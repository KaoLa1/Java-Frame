/**
 *新建 - 表格数据
 */
import React, { Component } from 'react'
import { axios } from '@/utils'
import Config from '@/config'
import {
    Modal,
    Button,
    Form,
    Input,
    message
} from 'antd'

const FormItem = Form.Item

class NewModal extends Component {
    constructor (props) {
        super(props)
        this.state = {
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
 * 新增
 */
addNewInfo(data) {
    let thiz = this
    axios.post(`${Config.API_BASE_URL}/bdsServiceInfos`,data).then((res) => {
    if(res.data === 1){
      message.success('新增成功!')
        thiz.closeModal(true, true)
    }else{
        message.success('新增失败!')
    }
})
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
        this.addNewInfo(value)
    }
})
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
            title='新增'
            closable={false}
            footer={[
                <Button
                    key='submit'
                    type='primary'
                    onClick={() => this.onSubmit(this)}
                >
                    提交
                </Button>,
                <Button
                    key='close'
                    type='danger'
                    onClick={() => { this.closeModal(false, true) }}
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
                            rules: [{ required: true, message: '请输入ͳ??????ID' }]
                        })(
                            <Input
                                placeholder='请输入ͳ??????ID'
                            />
                        )}
                    </FormItem>
                                    <FormItem
                        label='ͳ?Ʒ?ʽ'
                        {...formItemLayout}
                    >
                        {getFieldDecorator('statisticType', {
                            rules: [{ required: true, message: '请输入ͳ?Ʒ?ʽ' }]
                        })(
                            <Input
                                placeholder='请输入ͳ?Ʒ?ʽ'
                            />
                        )}
                    </FormItem>
                                    <FormItem
                        label='ͳ??????CODE'
                        {...formItemLayout}
                    >
                        {getFieldDecorator('statisticCategoryCode', {
                            rules: [{ required: true, message: '请输入ͳ??????CODE' }]
                        })(
                            <Input
                                placeholder='请输入ͳ??????CODE'
                            />
                        )}
                    </FormItem>
                                    <FormItem
                        label='ͳ?????'
                        {...formItemLayout}
                    >
                        {getFieldDecorator('statisticCategoryValue', {
                            rules: [{ required: true, message: '请输入ͳ?????' }]
                        })(
                            <Input
                                placeholder='请输入ͳ?????'
                            />
                        )}
                    </FormItem>
                                    <FormItem
                        label='ͳ?????'
                        {...formItemLayout}
                    >
                        {getFieldDecorator('statisticDate', {
                            rules: [{ required: true, message: '请输入ͳ?????' }]
                        })(
                            <Input
                                placeholder='请输入ͳ?????'
                            />
                        )}
                    </FormItem>
                                    <FormItem
                        label='ͳ????????'
                        {...formItemLayout}
                    >
                        {getFieldDecorator('statisticData', {
                            rules: [{ required: true, message: '请输入ͳ????????' }]
                        })(
                            <Input
                                placeholder='请输入ͳ????????'
                            />
                        )}
                    </FormItem>
                            </Form>
        </Modal>
    )
}
}

export default Form.create()(NewModal)
