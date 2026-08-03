package org.purpleBean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.model.core.type.UsageLimitsTotal;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("UsageLimits Domain Tests")
class UsageLimitsTest extends AbstractKmipStructureTestSuite<UsageLimits>
    implements KmipAttributeTestSuite<UsageLimits> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<UsageLimits> type() {
    return UsageLimits.class;
  }

  @Override
  public UsageLimits createDefault() {
    return UsageLimits
        .builder()
        .usageLimitsTotal(UsageLimitsTotal.of(100L))
        .usageLimitsCount(UsageLimitsCount.of(10L))
        .usageLimitsUnit(UsageLimitsUnit.Standard.BYTE.inst())
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 3;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values.get(0)).isInstanceOf(UsageLimitsTotal.class);
    assertThat(values.get(1)).isInstanceOf(UsageLimitsCount.class);
    assertThat(values.get(2)).isInstanceOf(UsageLimitsUnit.class);
  }

  @Override
  public boolean expectAlwaysPresent() {
    return false;
  }

  @Override
  public boolean expectServerInitializable() {
    return true;
  }

  @Override
  public boolean expectClientInitializable() {
    return true;
  }

  @Override
  public boolean expectClientDeletable() {
    return true;
  }

  @Override
  public boolean expectMultiInstanceAllowed() {
    return false;
  }

  @Override
  public State stateForServerModifiableTrue() {
    return null;
  }

  @Override
  public State stateForServerModifiableFalse() {
    return null;
  }

  @Override
  public State stateForClientModifiableTrue() {
    return null;
  }

  @Override
  public State stateForClientModifiableFalse() {
    return null;
  }

  @Override
  public AttributeValue expectedAttributeValue() {
    return AttributeValue.ofStructure(createDefault().getValue());
  }

  @Override
  public void attribute_serverModifiable_respectsState() {
  }

  @Override
  public void attribute_clientModifiable_respectsState() {
  }
}
