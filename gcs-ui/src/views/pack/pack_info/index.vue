<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="物品ID" prop="giftId">
        <el-input
          v-model="queryParams.giftId"
          placeholder="请输入物品ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物品名" prop="giftName">
        <el-input
          v-model="queryParams.giftName"
          placeholder="请输入物品名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物品尺寸" prop="giftSize">
        <el-input
          v-model="queryParams.giftSize"
          placeholder="请输入物品尺寸"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物品介绍" prop="giftIntroduction">
        <el-input
          v-model="queryParams.giftIntroduction"
          placeholder="请输入物品介绍"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物品库存量" prop="giftInventory">
        <el-input
          v-model="queryParams.giftInventory"
          placeholder="请输入物品库存量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物品单价" prop="giftPrice">
        <el-input
          v-model="queryParams.giftPrice"
          placeholder="请输入物品单价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物品适用性别" prop="gender">
        <el-select v-model="queryParams.gender" placeholder="请选择物品适用性别" clearable>
          <el-option
            v-for="dict in dict.type.sys_user_sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="批次" prop="batchId">
        <el-input
          v-model="queryParams.batchId"
          placeholder="请输入批次"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部门" prop="deptId">
        <el-select v-model="queryParams.deptId" placeholder="请选择部门" clearable>
          <el-option
            v-for="dict in dict.type.dept_type"
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
          v-hasPermi="['pack:pack_info:add']"
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
          v-hasPermi="['pack:pack_info:edit']"
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
          v-hasPermi="['pack:pack_info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pack:pack_info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pack_infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="物品ID" align="center" prop="giftId" />
      <el-table-column label="物品名" align="center" prop="giftName" />
      <el-table-column label="物品图片" align="center" prop="giftPicture" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.giftPicture" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="物品尺寸" align="center" prop="giftSize" />
      <el-table-column label="物品介绍" align="center" prop="giftIntroduction" />
      <el-table-column label="物品库存量" align="center" prop="giftInventory" />
      <el-table-column label="物品单价" align="center" prop="giftPrice" />
      <el-table-column label="物品适用性别" align="center" prop="gender">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.gender"/>
        </template>
      </el-table-column>
      <el-table-column label="批次" align="center" prop="batchId" />
      <el-table-column label="部门" align="center" prop="deptId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.dept_type" :value="scope.row.deptId"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pack:pack_info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pack:pack_info:remove']"
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

    <!-- 添加或修改礼包信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="物品名" prop="giftName">
          <el-input v-model="form.giftName" placeholder="请输入物品名" />
        </el-form-item>
        <el-form-item label="物品图片" prop="giftPicture">
          <image-upload v-model="form.giftPicture"/>
        </el-form-item>
        <el-form-item label="物品尺寸" prop="giftSize">
          <el-input v-model="form.giftSize" placeholder="请输入物品尺寸" />
        </el-form-item>
        <el-form-item label="物品介绍" prop="giftIntroduction">
          <el-input v-model="form.giftIntroduction" placeholder="请输入物品介绍" />
        </el-form-item>
        <el-form-item label="物品库存量" prop="giftInventory">
          <el-input v-model="form.giftInventory" placeholder="请输入物品库存量" />
        </el-form-item>
        <el-form-item label="物品单价" prop="giftPrice">
          <el-input v-model="form.giftPrice" placeholder="请输入物品单价" />
        </el-form-item>
        <el-form-item label="物品适用性别" prop="gender">
          <el-select v-model="form.gender" placeholder="请选择物品适用性别">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="批次" prop="batchId">
          <el-input v-model="form.batchId" placeholder="请输入批次" />
        </el-form-item>
        <el-form-item label="部门" prop="deptId">
          <el-select v-model="form.deptId" placeholder="请选择部门">
            <el-option
              v-for="dict in dict.type.dept_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
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
import { listPack_info, getPack_info, delPack_info, addPack_info, updatePack_info } from "@/api/pack/pack_info";

export default {
  name: "Pack_info",
  dicts: ['sys_user_sex', 'dept_type'],
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
      // 礼包信息表格数据
      pack_infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        giftId: null,
        giftName: null,
        giftPicture: null,
        giftSize: null,
        giftIntroduction: null,
        giftInventory: null,
        giftPrice: null,
        gender: null,
        batchId: null,
        deptId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        giftName: [
          { required: true, message: "物品名不能为空", trigger: "blur" }
        ],
        giftPicture: [
          { required: true, message: "物品图片不能为空", trigger: "blur" }
        ],
        giftIntroduction: [
          { required: true, message: "物品介绍不能为空", trigger: "blur" }
        ],
        giftInventory: [
          { required: true, message: "物品库存量不能为空", trigger: "blur" }
        ],
        giftPrice: [
          { required: true, message: "物品单价不能为空", trigger: "blur" }
        ],
        gender: [
          { required: true, message: "物品适用性别不能为空", trigger: "change" }
        ],
        batchId: [
          { required: true, message: "批次不能为空", trigger: "blur" }
        ],
        deptId: [
          { required: true, message: "部门不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询礼包信息列表 */
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
        giftId: null,
        giftName: null,
        giftPicture: null,
        giftSize: null,
        giftIntroduction: null,
        giftInventory: null,
        isDeleted: null,
        giftPrice: null,
        gender: null,
        batchId: null,
        deptId: null
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
      this.ids = selection.map(item => item.giftId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加礼包信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const giftId = row.giftId || this.ids
      getPack_info(giftId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改礼包信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.giftId != null) {
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
      const giftIds = row.giftId || this.ids;
      this.$modal.confirm('是否确认删除礼包信息编号为"' + giftIds + '"的数据项？').then(function() {
        return delPack_info(giftIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pack/pack_info/export', {
        ...this.queryParams
      }, `pack_info_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
