package com.ovchingus.persistence.CSV;

import com.ovchingus.persistence.CSV.model.StoreEntityCSV;
import com.ovchingus.persistence.settings.DaoSettings;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StoreDaoCSV extends ConnectionCSV<StoreEntityCSV, Integer> {

    private String filePath = DaoSettings.getCsvFilePath() + "stores.csv";
    private String tempPath = DaoSettings.getCsvFilePath() + "tempStores.csv";


    public void clearFile() {
        clear(filePath, tempPath);
    }

    @Override
    public void persist(StoreEntityCSV entity) {
        try {
            FileUtils.writeStringToFile(new File(filePath),
                    (entity.getId() + "," + entity.getName() + "," + entity.getAddress() + "\n"),
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clearFile();
    }

    @Override
    public void update(StoreEntityCSV entity) {
        List<StoreEntityCSV> list = findAll();
        StoreEntityCSV e = findById(entity.getId());
        delete(e);
        persist(entity);
        clearFile();
    }

    @Override
    public StoreEntityCSV findById(Integer id) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {

            for (CSVRecord record : csvParser) {
                Integer curId = Integer.parseInt(record.get(0));
                if (curId.equals(id))
                    return new StoreEntityCSV(Integer.parseInt(record.get(0)),
                            record.get(1), record.get(2));
            }
            clearFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(StoreEntityCSV entity) {
        File sourceFile = new File(filePath);
        File outputFile = new File(tempPath);

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));) {
            String line;
            String outputLine = entity.getId() + "," + entity.getName()
                    + "," + entity.getAddress();
            while ((line = reader.readLine()) != null) {
                if (!line.equals(outputLine)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            reader.close();
            writer.close();
            sourceFile.delete();
            outputFile.renameTo(sourceFile);
            clearFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<StoreEntityCSV> findAll() {
        List<StoreEntityCSV> list = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {

            for (CSVRecord csvRecord : csvParser) {
                list.add(new StoreEntityCSV(Integer.parseInt(csvRecord.get(0)),
                        csvRecord.get(1), csvRecord.get(2)));
            }
            clearFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void deleteAll() {
        File sourceFile = new File(filePath);
        File outputFile = new File(tempPath);

        try {
            if (!sourceFile.exists())
                sourceFile.createNewFile();
            if (!outputFile.exists())
                outputFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clearFile();
        sourceFile.delete();
        outputFile.renameTo(sourceFile);
    }

    @Override
    public StoreEntityCSV findByName(String name) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {

            for (CSVRecord record : csvParser) {
                if (record.get(1).equals(name))
                    return new StoreEntityCSV(Integer.parseInt(record.get(0)),
                            record.get(1), record.get(2));
            }
            clearFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}