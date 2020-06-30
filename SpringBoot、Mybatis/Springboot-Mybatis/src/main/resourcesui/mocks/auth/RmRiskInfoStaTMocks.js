/**
 * Mock数据生成器
 */
import Mock from 'mockjs'
import Config from '@/config'

/**
 * 表格模拟数据
 */
Mock.mock(`${Config.API_BASE_URL}/rmRiskInfoStaTs`, function () {
  let data = Mock.mock({
    'array|200': [{
	    	    	statisticId: 'statisticId',
	    	    	statisticType: 'statisticType',
	    	    	statisticCategoryCode: 'statisticCategoryCode',
	    	    	statisticCategoryValue: 'statisticCategoryValue',
	    	    	statisticDate: 'statisticDate',
	    	    	statisticData: 'statisticData',
	        }]
  })
  return {
    list: data['array'],
    total: data['array'].length
  }
})


/**
 * 表格模板TableTpl的假数据-每一调条数据的详情
 */
Mock.mock(RegExp(`${Config.API_BASE_URL}/rmRiskInfoStaTs/` + "*"), 'get', function () {
  let data = Mock.mock({
    'detail': {
       	    	statisticId: 'statisticId',
	    	    	statisticType: 'statisticType',
	    	    	statisticCategoryCode: 'statisticCategoryCode',
	    	    	statisticCategoryValue: 'statisticCategoryValue',
	    	    	statisticDate: 'statisticDate',
	    	    	statisticData: 'statisticData',
	        }
  })
  return {
    data: data['detail'],
    code: 200
  }
})
