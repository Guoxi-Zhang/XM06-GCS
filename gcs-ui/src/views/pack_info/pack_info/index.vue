<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="礼包编号" prop="packId">
        <el-input
          v-model="queryParams.packId"
          placeholder="请输入礼包编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批次号" prop="batchId">
        <el-input
          v-model="queryParams.batchId"
          placeholder="请输入批次号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学院年级" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入学院年级"
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
          v-hasPermi="['pack_info:pack_info:add']"
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
          v-hasPermi="['pack_info:pack_info:edit']"
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
          v-hasPermi="['pack_info:pack_info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pack_info:pack_info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pack_infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="礼包编号" align="center" prop="packId" />
      <el-table-column label="批次号" align="center" prop="batchId" />
      <el-table-column label="学院年级" align="center" prop="deptId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pack_info:pack_info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pack_info:pack_info:remove']"
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

    <!-- 添加或修改礼包设置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="批次号" prop="batchId">
          <el-input v-model="form.batchId" placeholder="请输入批次号" />
        </el-form-item>
        <el-form-item label="学院年级" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入学院年级" />
        </el-form-item>
        <el-divider content-position="center">礼包物品选择信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddPackGift">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeletePackGift">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="packGiftList" :row-class-name="rowPackGiftIndex" @selection-change="handlePackGiftSelectionChange" ref="packGift">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="物品编号" prop="giftId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.giftId" placeholder="请输入物品编号" />
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
import { listPack_info, getPack_info, delPack_info, addPack_info, updatePack_info } from "@/api/pack_info/pack_info";

export default {
  name: "Pack_info",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedPackGift: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 礼包设置表格数据
      pack_infoList: [],
      // 礼包物品选择表格数据
      packGiftList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        packId: null,
        batchId: null,
        deptId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        batchId: [
          { required: true, message: "批次号不能为空", trigger: "blur" }
        ],
        deptId: [
          { required: true, message: "学院年级不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询礼包设置列表 */
    getList() {
      this.loading = true;
      listPack_info(this.queryParams).then(response => {
        this.pack_infoList = response.rows;
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
        packId: null,
        batchId: null,
        deptId: null,
        isDeleted: null,
        createTime: null,
        updateTime: null
      };
      this.packGiftList = [];
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
      this.ids = selection.map(item => item.packId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加礼包设置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const packId = row.packId || this.ids
      getPack_info(packId).then(response => {
        this.form = response.data;
        this.packGiftList = response.data.packGiftList;
        this.open = true;
        this.title = "修改礼包设置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.packGiftList = this.packGiftList;
          if (this.form.packId != null) {
            updatePack_info(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPack_info(this.form).then(response => {
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
      const packIds = row.packId || this.ids;
      this.$modal.confirm('是否确认删除礼包设置编号为"' + packIds + '"的数据项？').then(function() {
        return delPack_info(packIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 礼包物品选择序号 */
    rowPackGiftIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 礼包物品选择添加按钮操作 */
    handleAddPackGift() {
      let obj = {};
      obj.giftId = "";
      obj.giftNum = "";
      this.packGiftList.push(obj);
    },
    /** 礼包物品选择删除按钮操作 */
    handleDeletePackGift() {
      if (this.checkedPackGift.length == 0) {
        this.$modal.msgError("请先选择要删除的礼包物品选择数据");
      } else {
        const packGiftList = this.packGiftList;
        const checkedPackGift = this.checkedPackGift;
        this.packGiftList = packGiftList.filter(function(item) {
          return checkedPackGift.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handlePackGiftSelectionChange(selection) {
      this.checkedPackGift = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pack_info/pack_info/export', {
        ...this.queryParams
      }, `pack_info_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
