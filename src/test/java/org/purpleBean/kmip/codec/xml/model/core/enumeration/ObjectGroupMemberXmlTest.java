package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ObjectGroupMember;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ObjectGroupMember XML Serialization")
class ObjectGroupMemberXmlTest extends AbstractXmlSerializationTestSuite<ObjectGroupMember> {
  @Override
  public Class<ObjectGroupMember> type() {
    return ObjectGroupMember.class;
  }

  @Override
  public ObjectGroupMember createDefault() {
    return ObjectGroupMember.Standard.GROUP_MEMBER_FRESH.inst();
  }

  @Override
  public ObjectGroupMember createVariant() {
    return ObjectGroupMember.Standard.GROUP_MEMBER_DEFAULT.inst();
  }
}
