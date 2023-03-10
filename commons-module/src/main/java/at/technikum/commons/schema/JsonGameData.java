/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package at.technikum.commons.schema;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class JsonGameData extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -3410763775156239586L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"JsonGameData\",\"namespace\":\"at.technikum.commons.schema\",\"fields\":[{\"name\":\"gameType\",\"type\":{\"type\":\"enum\",\"name\":\"GameType\",\"symbols\":[\"LEAGUE_OF_LEGENDS\",\"DOTA2\"]}},{\"name\":\"content\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<JsonGameData> ENCODER =
      new BinaryMessageEncoder<JsonGameData>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<JsonGameData> DECODER =
      new BinaryMessageDecoder<JsonGameData>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<JsonGameData> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<JsonGameData> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<JsonGameData>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this JsonGameData to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a JsonGameData from a ByteBuffer. */
  public static JsonGameData fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public at.technikum.commons.schema.GameType gameType;
  @Deprecated public java.lang.CharSequence content;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public JsonGameData() {}

  /**
   * All-args constructor.
   * @param gameType The new value for gameType
   * @param content The new value for content
   */
  public JsonGameData(at.technikum.commons.schema.GameType gameType, java.lang.CharSequence content) {
    this.gameType = gameType;
    this.content = content;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return gameType;
    case 1: return content;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: gameType = (at.technikum.commons.schema.GameType)value$; break;
    case 1: content = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'gameType' field.
   * @return The value of the 'gameType' field.
   */
  public at.technikum.commons.schema.GameType getGameType() {
    return gameType;
  }

  /**
   * Sets the value of the 'gameType' field.
   * @param value the value to set.
   */
  public void setGameType(at.technikum.commons.schema.GameType value) {
    this.gameType = value;
  }

  /**
   * Gets the value of the 'content' field.
   * @return The value of the 'content' field.
   */
  public java.lang.CharSequence getContent() {
    return content;
  }

  /**
   * Sets the value of the 'content' field.
   * @param value the value to set.
   */
  public void setContent(java.lang.CharSequence value) {
    this.content = value;
  }

  /**
   * Creates a new JsonGameData RecordBuilder.
   * @return A new JsonGameData RecordBuilder
   */
  public static at.technikum.commons.schema.JsonGameData.Builder newBuilder() {
    return new at.technikum.commons.schema.JsonGameData.Builder();
  }

  /**
   * Creates a new JsonGameData RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new JsonGameData RecordBuilder
   */
  public static at.technikum.commons.schema.JsonGameData.Builder newBuilder(at.technikum.commons.schema.JsonGameData.Builder other) {
    return new at.technikum.commons.schema.JsonGameData.Builder(other);
  }

  /**
   * Creates a new JsonGameData RecordBuilder by copying an existing JsonGameData instance.
   * @param other The existing instance to copy.
   * @return A new JsonGameData RecordBuilder
   */
  public static at.technikum.commons.schema.JsonGameData.Builder newBuilder(at.technikum.commons.schema.JsonGameData other) {
    return new at.technikum.commons.schema.JsonGameData.Builder(other);
  }

  /**
   * RecordBuilder for JsonGameData instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<JsonGameData>
    implements org.apache.avro.data.RecordBuilder<JsonGameData> {

    private at.technikum.commons.schema.GameType gameType;
    private java.lang.CharSequence content;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(at.technikum.commons.schema.JsonGameData.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.gameType)) {
        this.gameType = data().deepCopy(fields()[0].schema(), other.gameType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.content)) {
        this.content = data().deepCopy(fields()[1].schema(), other.content);
        fieldSetFlags()[1] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing JsonGameData instance
     * @param other The existing instance to copy.
     */
    private Builder(at.technikum.commons.schema.JsonGameData other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.gameType)) {
        this.gameType = data().deepCopy(fields()[0].schema(), other.gameType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.content)) {
        this.content = data().deepCopy(fields()[1].schema(), other.content);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'gameType' field.
      * @return The value.
      */
    public at.technikum.commons.schema.GameType getGameType() {
      return gameType;
    }

    /**
      * Sets the value of the 'gameType' field.
      * @param value The value of 'gameType'.
      * @return This builder.
      */
    public at.technikum.commons.schema.JsonGameData.Builder setGameType(at.technikum.commons.schema.GameType value) {
      validate(fields()[0], value);
      this.gameType = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'gameType' field has been set.
      * @return True if the 'gameType' field has been set, false otherwise.
      */
    public boolean hasGameType() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'gameType' field.
      * @return This builder.
      */
    public at.technikum.commons.schema.JsonGameData.Builder clearGameType() {
      gameType = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'content' field.
      * @return The value.
      */
    public java.lang.CharSequence getContent() {
      return content;
    }

    /**
      * Sets the value of the 'content' field.
      * @param value The value of 'content'.
      * @return This builder.
      */
    public at.technikum.commons.schema.JsonGameData.Builder setContent(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.content = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'content' field has been set.
      * @return True if the 'content' field has been set, false otherwise.
      */
    public boolean hasContent() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'content' field.
      * @return This builder.
      */
    public at.technikum.commons.schema.JsonGameData.Builder clearContent() {
      content = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JsonGameData build() {
      try {
        JsonGameData record = new JsonGameData();
        record.gameType = fieldSetFlags()[0] ? this.gameType : (at.technikum.commons.schema.GameType) defaultValue(fields()[0]);
        record.content = fieldSetFlags()[1] ? this.content : (java.lang.CharSequence) defaultValue(fields()[1]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<JsonGameData>
    WRITER$ = (org.apache.avro.io.DatumWriter<JsonGameData>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<JsonGameData>
    READER$ = (org.apache.avro.io.DatumReader<JsonGameData>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
