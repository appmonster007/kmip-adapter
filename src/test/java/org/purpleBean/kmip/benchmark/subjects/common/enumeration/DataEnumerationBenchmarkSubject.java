package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.DataEnumeration;

public class DataEnumerationBenchmarkSubject extends KmipBenchmarkSubject<DataEnumeration> {

    public DataEnumerationBenchmarkSubject() throws Exception {
        DataEnumeration dataEnumeration = new DataEnumeration(DataEnumeration.Standard.DECRYPT);
        initialize(dataEnumeration, DataEnumeration.class);
    }

    @Override
    public String name() {
        return "DataEnumeration";
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
