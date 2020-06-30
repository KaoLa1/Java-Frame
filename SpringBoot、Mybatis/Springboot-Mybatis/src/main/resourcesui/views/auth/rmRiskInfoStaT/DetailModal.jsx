/**
 *详情(弹窗) - 表格数据
 */
import React, { Component } from 'react'
import {
    Row,
    Col,
    Button,
    Modal
} from 'antd'
import { axios } from '@/utils'
import Config from '@/config'

/**
 * Col中-标签部分的样式
 */
const labelStyle = {
    textAlign: 'right',
    paddingRight: '1%'
}

class DetailModal extends Component {
    constructor (props) {
        super(props)
        this.state = {
            data: {}
        }
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
    /**
     * 工具-关闭弹窗
     */
    closeModal = (thiz) => {
    thiz.props.onClose()
}

/**
 * 工具-清理params
 */
clearParams = (thiz) => {
    // 清理state中的表单值
    thiz.setState({
        name: '',
        age: 1,
        address: ''
    })
}

/**
 * 事件-关闭
 */
onClose = () => {
    // 关闭弹窗
    this.closeModal(this)
    // 清理表单
    this.clearParams(this)
}
componentDidMount(){
    this.getDetailInfo(this.props.id)
}

componentWillReceiveProps (nextProps) {
    // 如果是显示该弹窗 + 并且是另外一行数据的id 才请求一遍数据
        // 获取当前行数据
        this.getDetailInfo(nextProps.id)
}

render () {
    const {
        visible
    } = this.props
    return (
        <Modal
        visible={visible}
        width={800}
        title='详情'
        closable={false}
        footer={[
            <Button
                key='close'
                type='danger'
                onClick={this.onClose}
            >
                关闭
            </Button>
        ]}
    >
        <Row>
        <Col
            span={4}
            offset={4}
            style={{ ...labelStyle }}
        >
            ͳ??????ID:
        </Col>
        <Col span={10}>
            {this.state.data['statisticId'] || '无'}
        </Col>
    </Row>
        <Row>
        <Col
            span={4}
            offset={4}
            style={{ ...labelStyle }}
        >
            ͳ?Ʒ?ʽ:
        </Col>
        <Col span={10}>
            {this.state.data['statisticType'] || '无'}
        </Col>
    </Row>
        <Row>
        <Col
            span={4}
            offset={4}
            style={{ ...labelStyle }}
        >
            ͳ??????CODE:
        </Col>
        <Col span={10}>
            {this.state.data['statisticCategoryCode'] || '无'}
        </Col>
    </Row>
        <Row>
        <Col
            span={4}
            offset={4}
            style={{ ...labelStyle }}
        >
            ͳ?????:
        </Col>
        <Col span={10}>
            {this.state.data['statisticCategoryValue'] || '无'}
        </Col>
    </Row>
        <Row>
        <Col
            span={4}
            offset={4}
            style={{ ...labelStyle }}
        >
            ͳ?????:
        </Col>
        <Col span={10}>
            {this.state.data['statisticDate'] || '无'}
        </Col>
    </Row>
        <Row>
        <Col
            span={4}
            offset={4}
            style={{ ...labelStyle }}
        >
            ͳ????????:
        </Col>
        <Col span={10}>
            {this.state.data['statisticData'] || '无'}
        </Col>
    </Row>
        </Modal>
    )
}
}

export default DetailModal
