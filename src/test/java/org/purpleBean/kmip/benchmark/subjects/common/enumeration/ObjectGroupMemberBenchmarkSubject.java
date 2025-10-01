package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.ObjectGroupMember;

public class ObjectGroupMemberBenchmarkSubject extends KmipBenchmarkSubject<ObjectGroupMember> {

    public ObjectGroupMemberBenchmarkSubject() throws Exception {
        ObjectGroupMember objectGroupMember = new ObjectGroupMember(ObjectGroupMember.Standard.GROUP_MEMBER_FRESH);
        initialize(objectGroupMember, ObjectGroupMember.class);
    }

    @Override
    public String name() {
        return "ObjectGroupMember";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
