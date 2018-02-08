package com.ronglian.fssc.webapp.external.manager.ebs.api;

import com.ronglian.fssc.webapp.external.vo.ebs.api.get_doc_sequence_value.GetDocSequenceValueVo;

public class GetDocSequenceValueManagerTest extends ISimpleEbsApiTest<GetDocSequenceValueVo> {

    @Override
    protected GetDocSequenceValueVo getApiRequestEntity() {
        GetDocSequenceValueVo vo = new GetDocSequenceValueVo();
        vo.setpFssNum("test");
        return vo;
    }

}
