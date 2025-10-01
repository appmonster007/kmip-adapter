package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.Fips186Variation;

public class Fips186VariationBenchmarkSubject extends KmipBenchmarkSubject<Fips186Variation> {

    public Fips186VariationBenchmarkSubject() throws Exception {
        Fips186Variation fips186Variation = new Fips186Variation(Fips186Variation.Standard.UNSPECIFIED);
        initialize(fips186Variation, Fips186Variation.class);
    }

    @Override
    public String name() {
        return "Fips186Variation";
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
