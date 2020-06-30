/**
 *编辑（弹窗） - 表格数据
 */
import React, { Component } from 'react'
import {
    Modal,
    Button,
    Form,
    Input,
    message
} from 'antd'
import { axios } from '@/utils'
import Config from '@/config'

const FormItem = Form.Item

class EditModal extends Component {
    constructor (props) {
        super(props)
        this.state = {
            data: {}
        }
    }
    /**
     * 工具-关闭弹窗
     * isRefreshTable：是否刷新表格数据
     * isClearForm：是否清空表单
     */
    closeModal = (isRefreshTable,isClearForm) => {
    this.props.onClose()
    if(isRefreshTable){
        this.props.refreshTable()
    }
    if(isClearForm){
        this.props.form.resetFields()
    }
}

/**
 * 事件-提交
 */
onSubmit = () => {
    let thiz = this
    // 校验参数
    thiz.props.form.validateFieldsAndScroll((errors, value) => {
        // 如果校验通过
        if (!errors) {
        this.updateInfo(value)
    }
})
}

/**
 * 更新数据
 */
updateInfo(data) {
    let thiz = this
    axios.put(`${Config.API_BASE_URL}/bdsServiceInfos`,data).then((res) => {
        if(res.data === 1){
        this.setState({
            data: {...this.state.data, ...data}
        }, () => {
            message.success('更新成功!')
            thiz.closeModal(true, true)
        })
    }else{
        message.success('更新失败!')
    }
})
}

/**
 * 初始化
 */
getDetailInfo(id) {
    let thiz = this
    axios.get(`${Config.API_BASE_URL}/bdsServiceInfos/${id}`).then((res) => {
        thiz.setState({
        data: res.data
    })
})
}

componentDidMount(){
    this.getDetailInfo(this.props.id)
}

componentWillReceiveProps (nextProps) {
    if (this.props.id !== nextProps.id ) {
        // 获取当前行数据
        this.getDetailInfo(nextProps.id)
    }
}

render () {
    const { getFieldDecorator } = this.props.form
    const { visible } = this.props
    // FormItem的样式-响应式布局
    const formItemLayout = {
        labelCol: {
            xs: { span: 24 },
            sm: { span: 8 }
        },
        wrapperCol: {
            xs: { span: 24 },
            sm: { span: 8 }
        }
    }
    return (
        <Modal
        visible={visible}
        width={800}
        title='编辑'
        closable={false}
        footer={[
            <Button
                key='submit'
                type='primary'
                onClick={() => this.onSubmit()}
            >
                提交
            </Button>,
            <Button
                key='close'
                type='danger'
                onClick={()=>{this.closeModal()}}
            >
                关闭
            </Button>
        ]}
    >
        <Form>
        <FormItem
        label='ͳ??????ID'
        {...formItemLayout}
    >
        {getFieldDecorator('statisticId', {
            rules: [{ required: true, message: '请输入ͳ??????ID' }],
            initialValue: this.state.data['statisticId'] || ''
        })(
            <Input
                placeholder='请输入ͳ??????ID'
                onChange={this.onNameChange}
            />
        )}
    </FormItem>
            <FormItem
        label='ͳ?Ʒ?ʽ'
        {...formItemLayout}
    >
        {getFieldDecorator('statisticType', {
            rules: [{ required: true, message: '请输入ͳ?Ʒ?ʽ' }],
            initialValue: this.state.data['statisticType'] || ''
        })(
            <Input
                placeholder='请输入ͳ?Ʒ?ʽ'
                onChange={this.onNameChange}
            />
        )}
    </FormItem>
            <FormItem
        label='ͳ??????CODE'
        {...formItemLayout}
    >
        {getFieldDecorator('statisticCategoryCode', {
            rules: [{ required: true, message: '请输入ͳ??????CODE' }],
            initialValue: this.state.data['statisticCategoryCode'] || ''
        })(
            <Input
                placeholder='请输入ͳ??????CODE'
                onChange={this.onNameChange}
            />
        )}
    </FormItem>
            <FormItem
        label='ͳ?????'
        {...formItemLayout}
    >
        {getFieldDecorator('statisticCategoryValue', {
            rules: [{ required: true, message: '请输入ͳ?????' }],
            initialValue: this.state.data['statisticCategoryValue'] || ''
        })(
            <Input
                placeholder='请输入ͳ?????'
                onChange={this.onNameChange}
            />
        )}
    </FormItem>
            <FormItem
        label='ͳ?????'
        {...formItemLayout}
    >
        {getFieldDecorator('statisticDate', {
            rules: [{ required: true, message: '请输入ͳ?????' }],
            initialValue: this.state.data['statisticDate'] || ''
        })(
            <Input
                placeholder='请输入ͳ?????'
                onChange={this.onNameChange}
            />
        )}
    </FormItem>
            <FormItem
        label='ͳ????????'
        {...formItemLayout}
    >
        {getFieldDecorator('statisticData', {
            rules: [{ required: true, message: '请输入ͳ????????' }],
            initialValue: this.state.data['statisticData'] || ''
        })(
            <Input
                placeholder='请输入ͳ????????'
                onChange={this.onNameChange}
            />
        )}
    </FormItem>
            </Form>
    </Modal>
    )
}
}

export default Form.create()(EditModal)
