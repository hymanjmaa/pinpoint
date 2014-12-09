/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.navercorp.pinpoint.thrift.dto;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TApiMetaData implements org.apache.thrift.TBase<TApiMetaData, TApiMetaData._Fields>, java.io.Serializable, Cloneable, Comparable<TApiMetaData> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TApiMetaData");

  private static final org.apache.thrift.protocol.TField AGENT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("agentId", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField AGENT_START_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("agentStartTime", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField API_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("apiId", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField API_INFO_FIELD_DESC = new org.apache.thrift.protocol.TField("apiInfo", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField LINE_FIELD_DESC = new org.apache.thrift.protocol.TField("line", org.apache.thrift.protocol.TType.I32, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TApiMetaDataStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TApiMetaDataTupleSchemeFactory());
  }

  private String agentId; // required
  private long agentStartTime; // required
  private int apiId; // required
  private String apiInfo; // required
  private int line; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    AGENT_ID((short)1, "agentId"),
    AGENT_START_TIME((short)2, "agentStartTime"),
    API_ID((short)4, "apiId"),
    API_INFO((short)5, "apiInfo"),
    LINE((short)6, "line");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // AGENT_ID
          return AGENT_ID;
        case 2: // AGENT_START_TIME
          return AGENT_START_TIME;
        case 4: // API_ID
          return API_ID;
        case 5: // API_INFO
          return API_INFO;
        case 6: // LINE
          return LINE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __AGENTSTARTTIME_ISSET_ID = 0;
  private static final int __APIID_ISSET_ID = 1;
  private static final int __LINE_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.LINE};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.AGENT_ID, new org.apache.thrift.meta_data.FieldMetaData("agentId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.AGENT_START_TIME, new org.apache.thrift.meta_data.FieldMetaData("agentStartTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.API_ID, new org.apache.thrift.meta_data.FieldMetaData("apiId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.API_INFO, new org.apache.thrift.meta_data.FieldMetaData("apiInfo", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.LINE, new org.apache.thrift.meta_data.FieldMetaData("line", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TApiMetaData.class, metaDataMap);
  }

  public TApiMetaData() {
  }

  public TApiMetaData(
    String agentId,
    long agentStartTime,
    int apiId,
    String apiInfo)
  {
    this();
    this.agentId = agentId;
    this.agentStartTime = agentStartTime;
    setAgentStartTimeIsSet(true);
    this.apiId = apiId;
    setApiIdIsSet(true);
    this.apiInfo = apiInfo;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TApiMetaData(TApiMetaData other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetAgentId()) {
      this.agentId = other.agentId;
    }
    this.agentStartTime = other.agentStartTime;
    this.apiId = other.apiId;
    if (other.isSetApiInfo()) {
      this.apiInfo = other.apiInfo;
    }
    this.line = other.line;
  }

  public TApiMetaData deepCopy() {
    return new TApiMetaData(this);
  }

  @Override
  public void clear() {
    this.agentId = null;
    setAgentStartTimeIsSet(false);
    this.agentStartTime = 0;
    setApiIdIsSet(false);
    this.apiId = 0;
    this.apiInfo = null;
    setLineIsSet(false);
    this.line = 0;
  }

  public String getAgentId() {
    return this.agentId;
  }

  public void setAgentId(String agentId) {
    this.agentId = agentId;
  }

  public void unsetAgentId() {
    this.agentId = null;
  }

  /** Returns true if field agentId is set (has been assigned a value) and false otherwise */
  public boolean isSetAgentId() {
    return this.agentId != null;
  }

  public void setAgentIdIsSet(boolean value) {
    if (!value) {
      this.agentId = null;
    }
  }

  public long getAgentStartTime() {
    return this.agentStartTime;
  }

  public void setAgentStartTime(long agentStartTime) {
    this.agentStartTime = agentStartTime;
    setAgentStartTimeIsSet(true);
  }

  public void unsetAgentStartTime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __AGENTSTARTTIME_ISSET_ID);
  }

  /** Returns true if field agentStartTime is set (has been assigned a value) and false otherwise */
  public boolean isSetAgentStartTime() {
    return EncodingUtils.testBit(__isset_bitfield, __AGENTSTARTTIME_ISSET_ID);
  }

  public void setAgentStartTimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __AGENTSTARTTIME_ISSET_ID, value);
  }

  public int getApiId() {
    return this.apiId;
  }

  public void setApiId(int apiId) {
    this.apiId = apiId;
    setApiIdIsSet(true);
  }

  public void unsetApiId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __APIID_ISSET_ID);
  }

  /** Returns true if field apiId is set (has been assigned a value) and false otherwise */
  public boolean isSetApiId() {
    return EncodingUtils.testBit(__isset_bitfield, __APIID_ISSET_ID);
  }

  public void setApiIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __APIID_ISSET_ID, value);
  }

  public String getApiInfo() {
    return this.apiInfo;
  }

  public void setApiInfo(String apiInfo) {
    this.apiInfo = apiInfo;
  }

  public void unsetApiInfo() {
    this.apiInfo = null;
  }

  /** Returns true if field apiInfo is set (has been assigned a value) and false otherwise */
  public boolean isSetApiInfo() {
    return this.apiInfo != null;
  }

  public void setApiInfoIsSet(boolean value) {
    if (!value) {
      this.apiInfo = null;
    }
  }

  public int getLine() {
    return this.line;
  }

  public void setLine(int line) {
    this.line = line;
    setLineIsSet(true);
  }

  public void unsetLine() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LINE_ISSET_ID);
  }

  /** Returns true if field line is set (has been assigned a value) and false otherwise */
  public boolean isSetLine() {
    return EncodingUtils.testBit(__isset_bitfield, __LINE_ISSET_ID);
  }

  public void setLineIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __LINE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case AGENT_ID:
      if (value == null) {
        unsetAgentId();
      } else {
        setAgentId((String)value);
      }
      break;

    case AGENT_START_TIME:
      if (value == null) {
        unsetAgentStartTime();
      } else {
        setAgentStartTime((Long)value);
      }
      break;

    case API_ID:
      if (value == null) {
        unsetApiId();
      } else {
        setApiId((Integer)value);
      }
      break;

    case API_INFO:
      if (value == null) {
        unsetApiInfo();
      } else {
        setApiInfo((String)value);
      }
      break;

    case LINE:
      if (value == null) {
        unsetLine();
      } else {
        setLine((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case AGENT_ID:
      return getAgentId();

    case AGENT_START_TIME:
      return Long.valueOf(getAgentStartTime());

    case API_ID:
      return Integer.valueOf(getApiId());

    case API_INFO:
      return getApiInfo();

    case LINE:
      return Integer.valueOf(getLine());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case AGENT_ID:
      return isSetAgentId();
    case AGENT_START_TIME:
      return isSetAgentStartTime();
    case API_ID:
      return isSetApiId();
    case API_INFO:
      return isSetApiInfo();
    case LINE:
      return isSetLine();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TApiMetaData)
      return this.equals((TApiMetaData)that);
    return false;
  }

  public boolean equals(TApiMetaData that) {
    if (that == null)
      return false;

    boolean this_present_agentId = true && this.isSetAgentId();
    boolean that_present_agentId = true && that.isSetAgentId();
    if (this_present_agentId || that_present_agentId) {
      if (!(this_present_agentId && that_present_agentId))
        return false;
      if (!this.agentId.equals(that.agentId))
        return false;
    }

    boolean this_present_agentStartTime = true;
    boolean that_present_agentStartTime = true;
    if (this_present_agentStartTime || that_present_agentStartTime) {
      if (!(this_present_agentStartTime && that_present_agentStartTime))
        return false;
      if (this.agentStartTime != that.agentStartTime)
        return false;
    }

    boolean this_present_apiId = true;
    boolean that_present_apiId = true;
    if (this_present_apiId || that_present_apiId) {
      if (!(this_present_apiId && that_present_apiId))
        return false;
      if (this.apiId != that.apiId)
        return false;
    }

    boolean this_present_apiInfo = true && this.isSetApiInfo();
    boolean that_present_apiInfo = true && that.isSetApiInfo();
    if (this_present_apiInfo || that_present_apiInfo) {
      if (!(this_present_apiInfo && that_present_apiInfo))
        return false;
      if (!this.apiInfo.equals(that.apiInfo))
        return false;
    }

    boolean this_present_line = true && this.isSetLine();
    boolean that_present_line = true && that.isSetLine();
    if (this_present_line || that_present_line) {
      if (!(this_present_line && that_present_line))
        return false;
      if (this.line != that.line)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(TApiMetaData other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetAgentId()).compareTo(other.isSetAgentId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAgentId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.agentId, other.agentId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAgentStartTime()).compareTo(other.isSetAgentStartTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAgentStartTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.agentStartTime, other.agentStartTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetApiId()).compareTo(other.isSetApiId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetApiId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.apiId, other.apiId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetApiInfo()).compareTo(other.isSetApiInfo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetApiInfo()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.apiInfo, other.apiInfo);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLine()).compareTo(other.isSetLine());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLine()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.line, other.line);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TApiMetaData(");
    boolean first = true;

    sb.append("agentId:");
    if (this.agentId == null) {
      sb.append("null");
    } else {
      sb.append(this.agentId);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("agentStartTime:");
    sb.append(this.agentStartTime);
    first = false;
    if (!first) sb.append(", ");
    sb.append("apiId:");
    sb.append(this.apiId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("apiInfo:");
    if (this.apiInfo == null) {
      sb.append("null");
    } else {
      sb.append(this.apiInfo);
    }
    first = false;
    if (isSetLine()) {
      if (!first) sb.append(", ");
      sb.append("line:");
      sb.append(this.line);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TApiMetaDataStandardSchemeFactory implements SchemeFactory {
    public TApiMetaDataStandardScheme getScheme() {
      return new TApiMetaDataStandardScheme();
    }
  }

  private static class TApiMetaDataStandardScheme extends StandardScheme<TApiMetaData> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TApiMetaData struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // AGENT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.agentId = iprot.readString();
              struct.setAgentIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // AGENT_START_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.agentStartTime = iprot.readI64();
              struct.setAgentStartTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // API_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.apiId = iprot.readI32();
              struct.setApiIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // API_INFO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.apiInfo = iprot.readString();
              struct.setApiInfoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // LINE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.line = iprot.readI32();
              struct.setLineIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TApiMetaData struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.agentId != null) {
        oprot.writeFieldBegin(AGENT_ID_FIELD_DESC);
        oprot.writeString(struct.agentId);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(AGENT_START_TIME_FIELD_DESC);
      oprot.writeI64(struct.agentStartTime);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(API_ID_FIELD_DESC);
      oprot.writeI32(struct.apiId);
      oprot.writeFieldEnd();
      if (struct.apiInfo != null) {
        oprot.writeFieldBegin(API_INFO_FIELD_DESC);
        oprot.writeString(struct.apiInfo);
        oprot.writeFieldEnd();
      }
      if (struct.isSetLine()) {
        oprot.writeFieldBegin(LINE_FIELD_DESC);
        oprot.writeI32(struct.line);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TApiMetaDataTupleSchemeFactory implements SchemeFactory {
    public TApiMetaDataTupleScheme getScheme() {
      return new TApiMetaDataTupleScheme();
    }
  }

  private static class TApiMetaDataTupleScheme extends TupleScheme<TApiMetaData> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TApiMetaData struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetAgentId()) {
        optionals.set(0);
      }
      if (struct.isSetAgentStartTime()) {
        optionals.set(1);
      }
      if (struct.isSetApiId()) {
        optionals.set(2);
      }
      if (struct.isSetApiInfo()) {
        optionals.set(3);
      }
      if (struct.isSetLine()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetAgentId()) {
        oprot.writeString(struct.agentId);
      }
      if (struct.isSetAgentStartTime()) {
        oprot.writeI64(struct.agentStartTime);
      }
      if (struct.isSetApiId()) {
        oprot.writeI32(struct.apiId);
      }
      if (struct.isSetApiInfo()) {
        oprot.writeString(struct.apiInfo);
      }
      if (struct.isSetLine()) {
        oprot.writeI32(struct.line);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TApiMetaData struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.agentId = iprot.readString();
        struct.setAgentIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.agentStartTime = iprot.readI64();
        struct.setAgentStartTimeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.apiId = iprot.readI32();
        struct.setApiIdIsSet(true);
      }
      if (incoming.get(3)) {
        struct.apiInfo = iprot.readString();
        struct.setApiInfoIsSet(true);
      }
      if (incoming.get(4)) {
        struct.line = iprot.readI32();
        struct.setLineIsSet(true);
      }
    }
  }

}

