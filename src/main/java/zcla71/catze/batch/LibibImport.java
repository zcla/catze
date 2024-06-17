package zcla71.catze.batch;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

import zcla71.catze.batch.model.LibibCsvLine;
import zcla71.catze.repository.Repository;
import zcla71.catze.repository.model.CatZeRepositoryData;
import zcla71.catze.service.model.Livro;
import zcla71.catze.service.model.ObraLiteraria;
import zcla71.catze.service.model.Pessoa;
import zcla71.util.Utils;

public class LibibImport {
    private static final String JSON_FILE_LOCATION = "data/catze.json"; // TODO Jogar pro application.properties

    public static void main(String[] args) throws URISyntaxException, IOException {
        List<LibibCsvLine> libib = readLibib();
        Repository<CatZeRepositoryData> repository = new Repository<CatZeRepositoryData>(CatZeRepositoryData.class, JSON_FILE_LOCATION, false);

        repository.beginTransaction();
        try {
            for (LibibCsvLine line : libib) {
                switch (line.getItemType()) {
                    case "book":
                        ObraLiteraria obra = new ObraLiteraria();
                        obra.setId(Utils.generateId());
                        obra.setTitulo(line.getTitle());
                        String[] autores = line.getCreators().split(",");
                        obra.setIdsAutores(new ArrayList<>());
                        for (String autor : autores) {
                            Pessoa pessoa = repository.getData().getPessoas().stream().filter(p -> p.getNome().equals(autor)).findFirst().orElse(null);
                            if (pessoa == null) {
                                pessoa = new Pessoa();
                                pessoa.setId(Utils.generateId());
                                pessoa.setNome(autor);
                                repository.getData().getPessoas().add(pessoa);
                            }
                            obra.getIdsAutores().add(pessoa.getId());
                        }
                        repository.getData().getObras().add(obra);

                        Livro livro = new Livro();
                        livro.setId(Utils.generateId());
                        livro.setTitulo(line.getTitle());
                        livro.setIdsObras(new ArrayList<>());
                        livro.getIdsObras().add(obra.getId());
                        livro.setIsbn13(line.getEanIsbn13());
                        repository.getData().getLivros().add(livro);
                        break;

                    default:
                        throw new RuntimeException("ItemType \"" + line.getItemType() + "\" desconhecido");
                }
            }
            repository.commitTransaction();
        } catch (Exception e) {
            repository.rollbackTransaction();
        }
    }

    private static List<LibibCsvLine> readLibib() throws URISyntaxException, IOException {
        File file = new File("data/libib.csv");
        Reader reader = Files.newBufferedReader(file.toPath());
        CSVReader csvReader = new CSVReader(reader);
        try {
            @SuppressWarnings({ "rawtypes", "unchecked" })
            List<LibibCsvLine> result = new CsvToBeanBuilder(csvReader).withType(LibibCsvLine.class).build().parse();
            return result;
        } finally {
            csvReader.close();
        }
    }
}
