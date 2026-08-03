package org.purplebean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.ObjectGroupMember;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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
