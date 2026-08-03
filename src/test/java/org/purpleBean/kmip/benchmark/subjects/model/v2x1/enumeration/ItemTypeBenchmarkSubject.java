package org.purplebean.kmip.benchmark.subjects.model.v2x1.enumeration;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.ItemType;

public class ItemTypeBenchmarkSubject extends KmipBenchmarkSubject<ItemType> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public ItemTypeBenchmarkSubject() throws Exception {
    ItemType subject = ItemType.Standard.STRUCTURE.inst();
    initialize(subject, ItemType.class);
  }

  @Override
  public String name() {
    return "ItemType";
  }
}