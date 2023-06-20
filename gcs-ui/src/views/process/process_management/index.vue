<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="98px">
      <el-form-item label="id" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生申请提交开始时间" prop="startTime">
        <el-date-picker clearable
          v-model="queryParams.startTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择学生申请提交开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="学生申请提交结束时间" prop="endTime">
        <el-date-picker clearable
          v-model="queryParams.endTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择学生申请提交结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="申请审核提交结束时间" prop="submitTime">
        <el-date-picker clearable
          v-model="queryParams.submitTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择申请审核提交结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="资金来源" prop="fundingSource">
        <el-select v-model="queryParams.fundingSource" placeholder="请选择资金来源" clearable>
          <el-option
            v-for="dict in dict.type.funding_source"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="批次" prop="batch">
        <el-input
          v-model="queryParams.batch"
          placeholder="请输入批次"
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
          v-hasPermi="['process:process_management:add']"
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
          v-hasPermi="['process:process_management:edit']"
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
          v-hasPermi="['process:process_management:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['process:process_management:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="process_managementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="" align="center" prop="id" />
      <el-table-column label="学生申请提交开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="学生申请提交结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="申请审核提交结束时间" align="center" prop="submitTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.submitTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="资金来源" align="center" prop="fundingSource">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.funding_source" :value="scope.row.fundingSource"/>
        </template>
      </el-table-column>
      <el-table-column label="批次" align="center" prop="batch" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['process:process_management:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['process:process_management:remove']"
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

    <!-- 添加或修改流程管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生申请提交开始时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择学生申请提交开始时间"
            :picker-options="startDatePicker">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="学生申请提交结束时间" prop="endTime">
          <el-date-picker clearable
            v-model="form.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择学生申请提交结束时间"
            :picker-options="endDatePicker">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="申请审核提交结束时间" prop="submitTime">
          <el-date-picker clearable
            v-model="form.submitTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择申请审核提交结束时间"
            :picker-options="submitDatePicker">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="资金来源" prop="fundingSource">
          <el-select v-model="form.fundingSource" placeholder="请选择资金来源">
            <el-option
              v-for="dict in dict.type.funding_source"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="批次" prop="batch">
          <el-input v-model="form.batch" placeholder="请输入批次" />
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
import { listProcess_management, getProcess_management, delProcess_management, addProcess_management, updateProcess_management } from "@/api/process/process_management";

export default {
  name: "Process_management",
  dicts: ['funding_source'],
  data() {
    return {
      startDatePicker: '',
      endDatePicker: this.processDate(),
      submitDatePicker: this.submitprocessDate(),
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
      // 流程管理表格数据
      process_managementList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        startTime: null,
        endTime: null,
        submitTime: null,
        fundingSource: null,
        batch: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        startTime: [
          { required: true, message: "学生申请提交开始时间不能为空", trigger: "blur" }
        ],
        endTime: [
          { required: true, message: "学生申请提交结束时间不能为空", trigger: "blur" }
        ],
        submitTime: [
          { required: true, message: "申请审核提交结束时间不能为空", trigger: "blur" }
        ],
        fundingSource: [
          { required: true, message: "资金来源不能为空", trigger: "change" }
        ],
        batch: [
          { required: true, message: "批次不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询流程管理列表 */
    getList() {
      this.loading = true;
      listProcess_management(this.queryParams).then(response => {
        this.process_managementList = response.rows;
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
        id: null,
        startTime: null,
        endTime: null,
        submitTime: null,
        fundingSource: null,
        batch: null,
        isDeleted: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加流程管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProcess_management(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改流程管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateProcess_management(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProcess_management(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除流程管理编号为"' + ids + '"的数据项？').then(function() {
        return delProcess_management(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('process/process_management/export', {
        ...this.queryParams
      }, `process_management_${new Date().getTime()}.xlsx`)
    },
    processDate () {
         const self = this;
         return {
           disabledDate (time) {
             // 如果开始时间不为空，则结束时间大于开始时间
             if (self.form.startTime) {
               return new Date(self.form.startTime).getTime() > time.getTime();
             } else {
               // 开始时间不选时，结束时间最大值小于等于当天日期
               return time.getTime() > Date.now();
             }
           }
         };
    },
    submitprocessDate () {
             const self = this;
             return {
               disabledDate (time) {
                 if (self.form.endTime) {
                   return new Date(self.form.endTime).getTime() > time.getTime();
                 } else {
                   return time.getTime() > Date.now();
                 }
               }
             };
    }
  }
};
</script>
