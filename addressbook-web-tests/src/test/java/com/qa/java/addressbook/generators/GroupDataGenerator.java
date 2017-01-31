package com.qa.java.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qa.java.addressbook.model.GroupData;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 29.01.2017.
 */
public class GroupDataGenerator {

  @Parameter (names = "-c", description = "Group count" )
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    GroupDataGenerator generator = new GroupDataGenerator();
   JCommander jCommander = new JCommander(generator);
   try {
     jCommander.parse(args);
   } catch (ParameterException ex) {
     jCommander.usage();
     return;
   }
    generator.run();
  }

  private void run() throws IOException {
    List<GroupData> groups = generateGroups(count);
    if (format.equals("csv")) {
      saveAsCsv(groups, new File(file));
    }else if (format.equals("xml")){
      saveAsXml(groups, new File(file));
    }else if (format.equals("json")){
      saveAsJson(groups, new File(file));
    } else {
      System.out.println("Unrecognized format" + format);
    }

  }

  private void saveAsJson(List<GroupData> groups, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create(); //для нормального форматирования в файле
    String json = gson.toJson(groups);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<GroupData> groups, File file) throws IOException {   //Генератор данных в XML
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class); // Даем тегу новое название group
    String xml = xstream.toXML(groups);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<GroupData>();
      for(int i=0; i< count; i++){
        groups.add(new GroupData().withName(String.format("test %s",i))
        .withHeader(String.format("header %s",i)).withFooter(String.format("footer %s",i)));
      }
    return groups;
  }

  private void saveAsCsv(List<GroupData> groups, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try (Writer writer = new FileWriter(file)) { //открыть файл для записи
      for (GroupData group : groups) {
        writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
      }
    }
  }
}
