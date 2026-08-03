package org.purpleBean.kmip.test.suite;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.BaseKmipTest;

/**
 * Reusable XML serialization/deserialization test suite for KMIP objects.
 */
@DisplayName("Abstract XML Serialization Suite")
public abstract class AbstractXmlSerializationTestSuite<T> extends BaseKmipTest
    implements KmipSerializationTestSuite<T, ObjectMapper, String> {

  @Override
  public ObjectMapper getMapper() {
    return getXmlMapper();
  }

  @Override
  public String serialize(T object) throws Exception {
    return getMapper().writeValueAsString(object);
  }

  @Override
  public T deserialize(String serialized) throws Exception {
    return getMapper().readValue(serialized, type());
  }

  @Override
  public void withKmipSpec(KmipSpec spec, Runnable operation) {
    super.withKmipSpec(spec, operation);
  }
}
