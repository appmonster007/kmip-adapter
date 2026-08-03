package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.ContactInformation;

public class ContactInformationBenchmarkSubject extends KmipBenchmarkSubject<ContactInformation> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public ContactInformationBenchmarkSubject() throws Exception {
    var fixed = "test";
    ContactInformation contactInformation = ContactInformation
        .builder()
        .value(fixed)
        .build();
    initialize(contactInformation, ContactInformation.class);
  }

  @Override
  public String name() {
    return "ContactInformation";
  }

}