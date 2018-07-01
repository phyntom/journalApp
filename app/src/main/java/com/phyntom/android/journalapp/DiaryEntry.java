package com.phyntom.android.journalapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity(tableName = "diary_entry")
public class DiaryEntry implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @TypeConverters(DateTypeConverter.class)
    @ColumnInfo(name = "entry_date")
    private LocalDate entryDatetime;

    @ColumnInfo(name = "entry_time")
    private String entryTime;

    @ColumnInfo(name = "content")
    private String content;

    @Ignore
    private String contentSummary;

    @Ignore
    public DiaryEntry() {
    }

    public DiaryEntry(LocalDate entryDatetime, String entryTime, String content) {
        this.entryDatetime = entryDatetime;
        this.entryTime = entryTime;
        this.content = content;
    }

    public LocalDate getEntryDatetime() {
        return entryDatetime;
    }

    public void setEntryDatetime(LocalDate entryDatetime) {
        this.entryDatetime = entryDatetime;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentSummary() {
        if (!content.isEmpty()) {
            contentSummary = getContent().substring(0, 30);
        }
        return contentSummary;
    }

    public void setContentSummary(String contentSummary) {
        this.contentSummary = contentSummary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DiaryEntry that = (DiaryEntry) o;
        return Objects.equals(getEntryTime(), that.getEntryTime()) && Objects.equals(getContent(), that.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntryTime(), getContent());
    }

    @Override
    public String toString() {
        return "DiaryEntry{" + "entryDatetime=" + entryDatetime + ", entryTime=" + entryTime + '}';
    }
}
