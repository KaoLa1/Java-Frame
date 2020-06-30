import React, { Component } from 'react'
import {
  Row,
  Col,
  Divider,
  Table,
  Form,
  Input,
  Button,
  Popconfirm,
  message
} from 'antd'
import { axios } from '@/utils'
import Config from '@/config'
import NewModal from './NewModal'
import EditModal from './EditModal'
import DetailModal from './DetailModal'
const { Item } = Form

/**
 * Form的一些布局配置
 */
const formLayout = {
  labelCol: { span: 6 },
  wrapperCol: { span: 16 }
}

const FilterForm = Form.create({ name: 'filterForm' })(
    // eslint-disable-next-line
    class extends React.Component {

        onReset = () => {
        this.props.form.resetFields()
    this.props.onSearch()
    }

render() {
    const { getFieldDecorator } = this.props.form
    return (
        <Form
            className="ant-advanced-search-form"
            onSubmit={this.handleSearch}
        >
            <Row gutter={24}>
                                    <Col span={6}>
                        <Item label='ͳ??????ID' {...formLayout} >
                            {getFieldDecorator(`statisticId`, {
                                rules: [{
                                    required: false
                                }],
                            })(
                                <Input placeholder='请输入搜索关键词' />
                            )}

                        </Item>
                    </Col>
                                    <Col span={6}>
                        <Item label='ͳ?Ʒ?ʽ' {...formLayout} >
                            {getFieldDecorator(`statisticType`, {
                                rules: [{
                                    required: false
                                }],
                            })(
                                <Input placeholder='请输入搜索关键词' />
                            )}

                        </Item>
                    </Col>
                                    <Col span={6}>
                        <Item label='ͳ??????CODE' {...formLayout} >
                            {getFieldDecorator(`statisticCategoryCode`, {
                                rules: [{
                                    required: false
                                }],
                            })(
                                <Input placeholder='请输入搜索关键词' />
                            )}

                        </Item>
                    </Col>
                                    <Col span={6}>
                        <Item label='ͳ?????' {...formLayout} >
                            {getFieldDecorator(`statisticCategoryValue`, {
                                rules: [{
                                    required: false
                                }],
                            })(
                                <Input placeholder='请输入搜索关键词' />
                            )}

                        </Item>
                    </Col>
                                    <Col span={6}>
                        <Item label='ͳ?????' {...formLayout} >
                            {getFieldDecorator(`statisticDate`, {
                                rules: [{
                                    required: false
                                }],
                            })(
                                <Input placeholder='请输入搜索关键词' />
                            )}

                        </Item>
                    </Col>
                                    <Col span={6}>
                        <Item label='ͳ????????' {...formLayout} >
                            {getFieldDecorator(`statisticData`, {
                                rules: [{
                                    required: false
                                }],
                            })(
                                <Input placeholder='请输入搜索关键词' />
                            )}

                        </Item>
                    </Col>
                            </Row>
            <Row>
                <Col span={24} style={{ textAlign: 'right' }}>
                    <Button type="primary" htmlType="submit" onClick={this.props.onSearch}>查询</Button>
                    <Button style={{ marginLeft: 8 }} onClick={this.onReset}>
                        重置
                    </Button>
                </Col>
            </Row>
        </Form>
    )
}
}
)

/**
 * ??????Ϣͳ?Ʊ 表格组件	
 *  @author admin
 *  @email admin@bonc.com.cn
 *  @date 2020-03-19 15:36:00
 */
class TableTpl extends Component {

  constructor(props) {
    super(props)
    this.state = {
      tableData: [], // 表格数据
      total: null, // 表格分页-数据的条数
      pageNum: 1, // 表格分页-第几页
      selectedRowKeys: [], // 删除的行的key值
      newModalVisible: false, // 新增-弹窗可见状态
      editModalVisible: false, // 编辑-弹窗可见状态
      detailModalVisible: false, // 详情-弹窗可见状态
      currentId: null // 当前选择的id（详情/编辑）
    }
  }

  /**
   * 新建
   */
  onAdd = () => {
    this.setState({
        newModalVisible: true
    })
  }

  /**
   * 表格事件-详情
   */
  onDetail = (record) => {
    this.setState({
        currentId: record['statisticId']
    }, () => {
        this.setState({
        detailModalVisible: true
    })
    })
  }

  /**
   * 表格事件-编辑
   */
  onEdit = (record) => {
    this.setState({
        currentId: record['statisticId']
    }, () => {
        this.setState({
        editModalVisible: true
    })
})
  }

  /**
   * 表格事件-删除（批量删除 单个删除则是[key]）
   * @param { array } keys 需要批量删除的key值数组
    */
   onDel = (record) => {
    let id = record['statisticId'] || ''
    axios.delete(`${Config.API_BASE_URL}/rmRiskInfoStaTs/${id}`).then((res) => {
        if(res.data === 1){
            message.success('删除成功!');
            //更新表格数据
        this.getTableData()
        }else{
            message.success('删除失败!');
        }
    })
   }

  /**
   * 表格事件-当选中项发生变化时调用
   */
  onTableSelectChange = (selectedRowKeys, selectedRows) => {
    this.setState({
      selectedRowKeys: selectedRowKeys
    })
  }

/**
 * 数据获取-获取表格数据
 */
getTableData = (params) => {
    if (!params) {
        params = {}
    }
    axios.get(`${Config.API_BASE_URL}/bdsServiceInfos`, {params: {...params}}).then((res) => {
        this.setState({
        tableData: res.data.list,
        total: res.data.total
    })
})
}

  getColums = () => {
    return ([
        	{
    		title: 'ͳ??????ID',
    		dataIndex: 'statisticId',
    		key: 'statisticId'
    	},
        	{
    		title: 'ͳ?Ʒ?ʽ',
    		dataIndex: 'statisticType',
    		key: 'statisticType'
    	},
        	{
    		title: 'ͳ??????CODE',
    		dataIndex: 'statisticCategoryCode',
    		key: 'statisticCategoryCode'
    	},
        	{
    		title: 'ͳ?????',
    		dataIndex: 'statisticCategoryValue',
    		key: 'statisticCategoryValue'
    	},
        	{
    		title: 'ͳ?????',
    		dataIndex: 'statisticDate',
    		key: 'statisticDate'
    	},
        	{
    		title: 'ͳ????????',
    		dataIndex: 'statisticData',
    		key: 'statisticData'
    	},
             {
        title: '操作',
        key: 'action',
        render: (text, record) => (
          <span>
            <a onClick={() => this.onDetail(record)}>详情</a>
            <Divider type="vertical" />
            <a onClick={() => this.onEdit(record)}>编辑</a>
            <Divider type="vertical" />
            <Popconfirm
              title="您确定要删除吗?"
              onConfirm={() => this.onDel(record)}
            >
              <a>删除</a>
            </Popconfirm>
          </span>
        )
      }]
    )
  }

  /**
   * 获取表格多选配置
   */
  getRowSelection = () => {
    return {
      selectedRowKeys: this.state.selectedRowKeys, // 选中的行的key
      onChange: this.onTableSelectChange // 当选中项发生变化时调用
    }
  }

  /**
   * 数据获取-当表格页码改变时回调
   */
  onPageChange = (page, pageSize) => {
    this.setState({
      pageNum: page,
      pageSize: pageSize
    }, function () {
      // 获取表格数据
      this.getTableData()
    })
  }

  /**
   * 获取表格分页器配置
   */
  getPagination = () => {
    const { pageNum, total } = this.state
    return {
      total: total, // 总数
      current: pageNum, // 当前页数
      pageSizeOptions: ['10', '20', '30', '40'],
      showQuickJumper: true, // 可以迅速跳页
      showSizeChanger: true, // 可以改变每页条数
      onChange: this.onPageChange
    }
  }

  componentDidMount () {
    // 获取表格数据
    this.getTableData()
    // 获取下拉框数据
  }

/**
 * 批量删除
 */
onBatchDel() {
    let selectedRecords = []
    for (let i = 0; i < this.state.selectedRowKeys.length; i++) {
        selectedRecords.push(this.state.tableData[this.state.selectedRowKeys[i]]['statisticId'])
    }
    axios.delete(`${Config.API_BASE_URL}/rmRiskInfoStaTs`, {
        params: {
            'statisticId': selectedRecords.join(',')
        }}).then((res) => {
        if (res.data === 1) {
        message.success('删除成功!')
        this.getTableData(this.formRef.props.form.getFieldsValue())
    } else {
        message.error('删除失败!')
    }
    })
}

onSearch = () => {
    this.getTableData(this.formRef.props.form.getFieldsValue())
}

saveFormRef = (formRef) => {
    this.formRef = formRef
}
  
  render () {
    const { tableData, newModalVisible, currentId, editModalVisible, detailModalVisible } = this.state
    return (
    <div>
        <FilterForm
            onSearch={this.onSearch}
            wrappedComponentRef={this.saveFormRef}
        />
            <Row>
                <Col span={24}>
                    <div className="table-operations">
                        <Button onClick={this.onAdd} >新增 </Button>
                        <Popconfirm
                            title="您确定要删除吗?"
                            onConfirm={() => this.onBatchDel(this.state.selectedRowKeys)}
                        >
                            <Button type='danger' disable={!(this.state.selectedRowKeys.length > 0)}>
                                批量删除
                            </Button>
                        </Popconfirm>
                    </div>
                    <Table
                        rowKey={record => record['STATISTIC_ID']}
                        columns={this.getColums()}
                        dataSource={tableData}
                        rowSelection={this.getRowSelection()}
                        pagination={this.getPagination()}
                    />
                </Col>
            </Row>

        <NewModal
            visible={newModalVisible}
            refreshTable={()=>{this.getTableData()}}
            onClose={() => { this.setState({ newModalVisible: false }) }}
        />
        <EditModal
            visible={editModalVisible}
            refreshTable={()=>{this.getTableData()}}
            id={currentId}
            onClose={() => { this.setState({ editModalVisible: false }) }}
        />
        <DetailModal
            visible={detailModalVisible}
            id={currentId}
            onClose={() => { this.setState({ detailModalVisible: false }) }}
        />
    </div>)
  }
}

export default TableTpl
