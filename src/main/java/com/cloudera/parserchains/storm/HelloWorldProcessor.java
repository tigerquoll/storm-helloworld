package com.cloudera.parserchains.storm;

import org.apache.storm.kafka.bolt.mapper.FieldNameBasedTupleToKafkaMapper;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;

import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

public class HelloWorldProcessor extends BaseRichBolt {
  OutputCollector outputCollector;

  public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
    this.outputCollector = outputCollector;
  }

  public void execute(Tuple tuple) {
    final String inputData = tuple.getStringByField("value");
    final String outputData = inputData;
    outputCollector.emit(tuple, new Values( "Hello world!," + outputData));
    outputCollector.ack(tuple);
  }

  public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    outputFieldsDeclarer.declare(new Fields(FieldNameBasedTupleToKafkaMapper.BOLT_MESSAGE));
  }

  @Override
  public void cleanup() {
    // for now do nothing, but in the future cleanly close down
  }
}
