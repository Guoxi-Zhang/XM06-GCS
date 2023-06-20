<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="审核的申请表ID" prop="applyId">
        <el-input
          v-model="queryParams.applyId"
          placeholder="请输入审核的申请表ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请类型" prop="applyType">
        <el-select v-model="queryParams.applyType" placeholder="请选择申请类型" clearable>
          <el-option
            v-for="dict in dict.type.apply_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审核人员ID" prop="verifyPersonId">
        <el-input
          v-model="queryParams.verifyPersonId"
          placeholder="请输入审核人员ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核操作" prop="verifyAction">
        <el-select v-model="queryParams.verifyAction" placeholder="请选择审核操作：0未操作，1不通过，2驳回，3通过" clearable>
          <el-option
            v-for="dict in dict.type.verify_action"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审核意见" prop="verifyAdvice">
        <el-input
          v-model="queryParams.verifyAdvice"
          placeholder="请输入审核意见"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['verify:verify_history:add']"
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
          v-hasPermi="['verify:verify_history:edit']"
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
          v-hasPermi="['verify:verify_history:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['verify:verify_history:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="verify_historyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="审核历史ID" align="center" prop="verifyId" />
      <el-table-column label="审核的申请表ID" align="center" prop="applyId" />
      <el-table-column label="申请类型" align="center" prop="applyType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.apply_type" :value="scope.row.applyType"/>
        </template>
      </el-table-column>
      <el-table-column label="审核人员ID" align="center" prop="verifyPersonId" />
      <el-table-column label="审核操作" align="center" prop="verifyAction">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.verify_action" :value="scope.row.verifyAction"/>
        </template>
      </el-table-column>
      <el-table-column label="审核意见" align="center" prop="verifyAdvice" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['verify:verify_history:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['verify:verify_history:remove']"
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

    <!-- 添加或修改审核历史对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="审核的申请表ID" prop="applyId">
          <el-input v-model="form.applyId" placeholder="请输入审核的申请表ID" />
        </el-form-item>
        <el-form-item label="申请类型" prop="applyType">
          <el-select v-model="form.applyType" placeholder="请选择申请类型">
            <el-option
              v-for="dict in dict.type.apply_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核人员ID" prop="verifyPersonId">
          <el-input v-model="form.verifyPersonId" placeholder="请输入审核人员ID" />
        </el-form-item>
        <el-form-item label="审核操作" prop="verifyAction">
          <el-select v-model="form.verifyAction" placeholder="请选择审核操作">
            <el-option
              v-for="dict in dict.type.verify_action"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核意见" prop="verifyAdvice">
          <el-input v-model="form.verifyAdvice" placeholder="请输入审核意见" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listVerify_history, getVerify_history, delVerify_history, addVerify_history, updateVerify_history } from "@/api/verify/verify_history";

export default {
  name: "Verify_history",
  dicts: ['apply_type', 'sys_yes_no', 'verify_action'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 审核历史表格数据
      verify_historyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        applyId: null,
        applyType: null,
        verifyPersonId: null,
        verifyAction: null,
        verifyAdvice: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        applyId: [
          { required: true, message: "审核的申请表ID", trigger: "blur" }
        ],
        applyType: [
          { required: true, message: "申请类型", trigger: "change" }
        ],
        verifyPersonId: [
          { required: true, message: "审核人员ID", trigger: "blur" }
        ],
        verifyAction: [
          { required: true, message: "审核操作", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询审核历史列表 */
    getList() {
      this.loading = true;
      listVerify_history(this.queryParams).then(response => {
        this.verify_historyList = response.rows;
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
        verifyId: null,
        applyId: null,
        applyType: null,
        verifyPersonId: null,
        verifyAction: null,
        verifyAdvice: null,
        isDeleted: null,
        createTime: null,
        updateTime: null
      };
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
      this.ids = selection.map(item => item.verifyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加审核历史";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const verifyId = row.verifyId || this.ids
      getVerify_history(verifyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改审核历史";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.verifyId != null) {
            updateVerify_history(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVerify_history(this.form).then(response => {
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
      const verifyIds = row.verifyId || this.ids;
      this.$modal.confirm('是否确认删除审核历史编号为"' + verifyIds + '"的数据项？').then(function() {
        return delVerify_history(verifyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('verify/verify_history/export', {
        ...this.queryParams
      }, `verify_history_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
