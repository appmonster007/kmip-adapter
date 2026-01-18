package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ObjectGroupMember;

public class ObjectGroupMemberBenchmarkSubject extends KmipBenchmarkSubject<ObjectGroupMember> {

    public ObjectGroupMemberBenchmarkSubject() throws Exception {
        ObjectGroupMember objectGroupMember = ObjectGroupMember.Standard.GROUP_MEMBER_FRESH.inst();
        initialize(objectGroupMember, ObjectGroupMember.class);
    }

    @Override
    public String name() {
        return "ObjectGroupMember";
    }

}
