package org.purpleBean.kmip.benchmark;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.test.KmipTestDataFactory;

import java.util.concurrent.TimeUnit;

/**
 * JMH benchmarks for KMIP serialization and deserialization operations.
 *
 * <p>This benchmark suite measures the performance of various KMIP operations including: - Object
 * creation (State, ActivationDateAttribute, SampleStructure) - Serialization/deserialization
 * to/from JSON and XML - Throughput and concurrent access patterns
 */
@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(1)
@Threads(1)
public class KmipSerializationBenchmark {

    private ObjectMapper jsonMapper;
    private ObjectMapper xmlMapper;

    private org.purpleBean.kmip.common.enumeration.State state;
    private ActivationDateAttribute activationDateAttribute;
    private SampleStructure sampleStructure;

    @Setup
    public void setup() {
        state = KmipTestDataFactory.createState();
        activationDateAttribute = KmipTestDataFactory.createActivationDateAttribute();
        sampleStructure = KmipTestDataFactory.createSampleStructure();

        // Use the same mappers as the rest of the test suite
        jsonMapper = new ObjectMapper();
        xmlMapper = new XmlMapper();
    }

    @Benchmark
    public org.purpleBean.kmip.common.enumeration.State stateCreation() {
        return KmipTestDataFactory.createState();
    }

    @Benchmark
    public ActivationDateAttribute activationDateCreation() {
        return KmipTestDataFactory.createActivationDateAttribute();
    }

    @Benchmark
    public SampleStructure sampleStructureCreation() {
        return KmipTestDataFactory.createSampleStructure();
    }

    @Benchmark
    public String stateToString() {
        return state.toString();
    }

    @Benchmark
    public String activationDateToString() {
        return activationDateAttribute.toString();
    }

    @Benchmark
    public String sampleStructureToString() {
        return sampleStructure.toString();
    }

    @Benchmark
    public KmipTag createKmipTag() {
        return new KmipTag(KmipTag.Standard.ACTIVATION_DATE);
    }

    @Benchmark
    public KmipSpec getKmipSpec() {
        return KmipSpec.V1_2;
    }

    @Benchmark
    public String serializeSampleStructureToJson() throws JsonProcessingException {
        return jsonMapper.writeValueAsString(sampleStructure);
    }

    @Benchmark
    public String serializeSampleStructureToXml() throws JsonProcessingException {
        return xmlMapper.writeValueAsString(sampleStructure);
    }

    @Benchmark
    public SampleStructure deserializeSampleStructureFromJson() throws JsonProcessingException {
        String json = jsonMapper.writeValueAsString(sampleStructure);
        return jsonMapper.readValue(json, SampleStructure.class);
    }

    @Benchmark
    public SampleStructure deserializeSampleXmlRoundTrip() throws JsonProcessingException {
        String xml = xmlMapper.writeValueAsString(sampleStructure);
        return xmlMapper.readValue(xml, SampleStructure.class);
    }

    @Benchmark
    @Threads(4)
    public void concurrentSampleStructureCreation(Blackhole bh) {
        SampleStructure structure = KmipTestDataFactory.createSampleStructure();
        bh.consume(structure);
    }

    @Benchmark
    public void measureHashCode(Blackhole bh) {
        bh.consume(sampleStructure.hashCode());
    }

    @Benchmark
    public void measureEquals(Blackhole bh) {
        SampleStructure another = KmipTestDataFactory.createSampleStructure();
        bh.consume(sampleStructure.equals(another));
    }

    @Benchmark
    public void measureSerializationRoundTrip(Blackhole bh) throws JsonProcessingException {
        String json = jsonMapper.writeValueAsString(sampleStructure);
        SampleStructure deserialized = jsonMapper.readValue(json, SampleStructure.class);
        bh.consume(deserialized);
    }
}
