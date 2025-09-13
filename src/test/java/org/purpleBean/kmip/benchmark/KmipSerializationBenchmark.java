package org.purpleBean.kmip.benchmark;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.openjdk.jmh.annotations.*;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.test.KmipTestDataFactory;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class KmipSerializationBenchmark {
    
    private ObjectMapper jsonMapper;
    private ObjectMapper xmlMapper;
    
    private org.purpleBean.kmip.common.enumeration.State state;
    private ActivationDateAttribute activationDateAttribute;
    private SampleStructure sampleStructure;
    
    @Setup
    public void setup() throws Exception {
        state = KmipTestDataFactory.createState();
        activationDateAttribute = KmipTestDataFactory.createActivationDateAttribute();
        sampleStructure = KmipTestDataFactory.createSampleStructure();
        
        // Initialize mappers with KMIP modules
        jsonMapper = new ObjectMapper();
        xmlMapper = new XmlMapper();
        
        // Register KMIP modules (assuming they're auto-discovered)
        jsonMapper.findAndRegisterModules();
        xmlMapper.findAndRegisterModules();
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
    public SampleStructure deserializeSampleStructureFromJson() throws JsonProcessingException {
        String json = jsonMapper.writeValueAsString(sampleStructure);
        return jsonMapper.readValue(json, SampleStructure.class);
    }
    
    @Benchmark
    public String serializeSampleStructureToXml() throws JsonProcessingException {
        return xmlMapper.writeValueAsString(sampleStructure);
    }
    
    @Benchmark
    public SampleStructure deserializeSampleStructureFromXml() throws JsonProcessingException {
        String xml = xmlMapper.writeValueAsString(sampleStructure);
        return xmlMapper.readValue(xml, SampleStructure.class);
    }
    
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public long throughputSampleStructureCreation() {
        SampleStructure structure = KmipTestDataFactory.createSampleStructure();
        return structure.hashCode(); // Prevent dead code elimination
    }
    
    @Benchmark
    @Threads(4)
    public void concurrentSampleStructureCreation(Blackhole bh) {
        SampleStructure structure = KmipTestDataFactory.createSampleStructure();
        bh.consume(structure);
    }
}
