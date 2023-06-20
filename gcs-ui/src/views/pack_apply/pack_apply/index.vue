<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="申请编号" prop="tableId">
        <el-input
          v-model="queryParams.tableId"
          placeholder="请输入申请编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生学号" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学生学号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请人学号/工号" prop="operatorId">
        <el-input
          v-model="queryParams.operatorId"
          placeholder="请输入申请人学号/工号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请单位" prop="operatorType">
        <el-select v-model="queryParams.operatorType" placeholder="请选择申请单位" clearable>
          <el-option
            v-for="dict in dict.type.applicant_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="申请原因" prop="applyReason">
        <el-input
          v-model="queryParams.applyReason"
          placeholder="请输入申请原因"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请批次" prop="batchId">
        <el-input
          v-model="queryParams.batchId"
          placeholder="请输入申请批次"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核单位" prop="nowStep">
        <el-select v-model="queryParams.nowStep" placeholder="请选择审核单位" clearable>
          <el-option
            v-for="dict in dict.type.verify_unit"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="申请状态" prop="applyState">
        <el-select v-model="queryParams.applyState" placeholder="请选择申请状态" clearable>
          <el-option
            v-for="dict in dict.type.verify_state"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['pack_apply:pack_apply:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['pack_apply:pack_apply:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['pack_apply:pack_apply:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pack_apply:pack_apply:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pack_applyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="申请编号" align="center" prop="tableId" />
      <el-table-column label="学生学号" align="center" prop="studentId" />
      <el-table-column label="申请人学号/工号" align="center" prop="operatorId" />
      <el-table-column label="申请单位" align="center" prop="operatorType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.applicant_type" :value="scope.row.operatorType"/>
        </template>
      </el-table-column>
      <el-table-column label="申请原因" align="center" prop="applyReason" />
      <el-table-column label="申请批次" align="center" prop="batchId" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核单位" align="center" prop="nowStep">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.verify_unit" :value="scope.row.nowStep"/>
        </template>
      </el-table-column>
      <el-table-column label="申请状态" align="center" prop="applyState">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.verify_state" :value="scope.row.applyState"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pack_apply:pack_apply:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pack_apply:pack_apply:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改大礼包申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生学号" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生学号" />
        </el-form-item>
        <el-form-item label="申请人学号/工号" prop="operatorId">
          <el-input v-model="form.operatorId" placeholder="请输入申请人学号/工号" />
        </el-form-item>
        <el-form-item label="申请单位" prop="operatorType">
          <el-select v-model="form.operatorType" placeholder="请选择申请单位">
            <el-option
              v-for="dict in dict.type.applicant_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="申请原因" prop="applyReason">
          <el-input v-model="form.applyReason" placeholder="请输入申请原因" />
        </el-form-item>
        <el-form-item label="申请批次" prop="batchId">
          <el-input v-model="form.batchId" placeholder="请输入申请批次" />
        </el-form-item>
        <el-divider content-position="center">物品选择列表信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddPackList">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeletePackList">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="packListList" :row-class-name="rowPackListIndex" @selection-change="handlePackListSelectionChange" ref="packList">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="申请物品" prop="giftId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.giftId" placeholder="请输入申请物品" />
            </template>
          </el-table-column>
          <el-table-column label="物品数量" prop="giftNum" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.giftNum" placeholder="请输入物品数量" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPack_apply, getPack_apply, delPack_apply, addPack_apply, updatePack_apply } from "@/api/pack_apply/pack_apply";

export default {
  name: "Pack_apply",
  dicts: ['verify_unit', 'verify_state', 'applicant_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedPackList: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 大礼包申请表格数据
      pack_applyList: [],
      // 物品选择列表表格数据
      packListList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        tableId: null,
        studentId: null,
        operatorId: null,
        operatorType: null,
        applyReason: null,
        batchId: null,
        nowStep: null,
        applyState: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        studentId: [
          { required: true, message: "学生学号不能为空", trigger: "blur" }
        ],
        operatorId: [
          { required: true, message: "申请人学号/工号不能为空", trigger: "blur" }
        ],
        operatorType: [
          { required: true, message: "申请单位不能为空", trigger: "change" }
        ],
        batchId: [
          { required: true, message: "申请批次不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询大礼包申请列表 */
    getList() {
      this.loading = true;
      listPack_apply(this.queryParams).then(response => {
        this.pack_applyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        tableId: null,
        studentId: null,
        operatorId: null,
        operatorType: null,
        applyReason: null,
        batchId: null,
        createTime: null,
        updateTime: null,
        isDeleted: null,
        nowStep: null,
        applyState: null
      };
      this.packListList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.tableId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加大礼包申请";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const tableId = row.tableId || this.ids
      getPack_apply(tableId).then(response => {
        this.form = response.data;
        this.packListList = response.data.packListList;
        this.open = true;
        this.title = "修改大礼包申请";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.packListList = this.packListList;
          if (this.form.tableId != null) {
            updatePack_apply(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPack_apply(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const tableIds = row.tableId || this.ids;
      this.$modal.confirm('是否确认删除大礼包申请编号为"' + tableIds + '"的数据项？').then(function() {
        return delPack_apply(tableIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 物品选择列表序号 */
    rowPackListIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 物品选择列表添加按钮操作 */
    handleAddPackList() {
      let obj = {};
      obj.giftId = "";
      obj.giftNum = "";
      this.packListList.push(obj);
    },
    /** 物品选择列表删除按钮操作 */
    handleDeletePackList() {
      if (this.checkedPackList.length == 0) {
        this.$modal.msgError("请先选择要删除的物品选择列表数据");
      } else {
        const packListList = this.packListList;
        const checkedPackList = this.checkedPackList;
        this.packListList = packListList.filter(function(item) {
          return checkedPackList.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handlePackListSelectionChange(selection) {
      this.checkedPackList = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pack_apply/pack_apply/export', {
        ...this.queryParams
      }, `pack_apply_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
