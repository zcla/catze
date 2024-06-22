package zcla71.baudoze.batch;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

import zcla71.baudoze.batch.model.LibibCsvLine;
import zcla71.baudoze.batch.model.Notes;
import zcla71.baudoze.repository.Repository;
import zcla71.baudoze.repository.model.BauDoZeRepositoryData;
import zcla71.baudoze.service.model.Colecao;
import zcla71.baudoze.service.model.Editora;
import zcla71.baudoze.service.model.Etiqueta;
import zcla71.baudoze.service.model.Livro;
import zcla71.baudoze.service.model.ObraLiteraria;
import zcla71.baudoze.service.model.Pessoa;
import zcla71.util.Utils;

public class LibibImport {
    private static final String JSON_FILE_LOCATION = "data/baudoze.json"; // TODO Jogar pro application.properties

    public static void main(String[] args) throws URISyntaxException, IOException {
        List<LibibCsvLine> libib = readLibib();
        Repository<BauDoZeRepositoryData> repository = new Repository<BauDoZeRepositoryData>(BauDoZeRepositoryData.class, JSON_FILE_LOCATION, false);

        repository.beginTransaction();
        try {
            for (LibibCsvLine line : libib) {
                switch (line.getItemType()) {
                    case "book":
                        String publisher = line.getPublisher();
                        Integer publishDate = null;
                        try {
                            publishDate = Integer.parseInt(line.getPublishDate().split("-")[0]);
                        } catch (NumberFormatException e) {
                            // ignora erros de parsing
                        }
                        Integer length = null;
                        try {
                            length = Integer.parseInt(line.getLength());
                        } catch (NumberFormatException e) {
                            // ignora erros de parsing
                        }
                        String edicao = null;
                        if ((line.getNotes() != null) && (line.getNotes().length() > 0)) {
                            try {
                                ObjectMapper objectMapper = new ObjectMapper();
                                Notes notes = objectMapper.readValue(line.getNotes(), Notes.class);
                                // ignora notes.id
                                edicao = notes.getEdicao();
                                if (notes.getEditora() != null) {
                                    publisher = notes.getEditora();
                                }
                                if (notes.getPublicacao() != null) {
                                    publishDate = notes.getPublicacao();
                                }
                                if (notes.getPaginas() != null) {
                                    length = notes.getPaginas();
                                }
                            } catch (JsonParseException e) {
                                System.err.println("Erro ao fazer o parsing da nota \"" + line.getNotes() + "\":");
                                System.err.println(e);
                            }
                        }

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
                        if ((line.getEanIsbn13() != null) && (line.getEanIsbn13().length() > 0)) {
                            livro.setIsbn13(line.getEanIsbn13());
                        }
                        if ((line.getUpcIsbn10() != null) && (line.getUpcIsbn10().length() > 0)) {
                            livro.setIsbn10(line.getUpcIsbn10());
                        }
                        livro.setIdsEditoras(new ArrayList<>());
                        if ((publisher != null) && (publisher.length() > 0)) {
                            String[] editoras = publisher.split(",");
                            for (String nomeEditora : editoras) {
                                Editora editora = repository.getData().buscaEditoraPorNome(nomeEditora);
                                if (editora == null) {
                                    editora = new Editora();
                                    editora.setId(Utils.generateId());
                                    editora.setNome(nomeEditora);
                                    repository.getData().getEditoras().add(editora);
                                }
                                livro.getIdsEditoras().add(editora.getId());
                            }
                        }
                        livro.setAno(publishDate);
                        livro.setPaginas(length);
                        livro.setEdicao(edicao);
                        // TODO added
                        repository.getData().getLivros().add(livro);

                        if ((line.getGroup() != null) && (line.getGroup().length() > 0)) {
                            Colecao colecao = repository.getData().buscaColecaoPorNome(line.getGroup());
                            if (colecao == null) {
                                colecao = new Colecao();
                                colecao.setId(Utils.generateId());
                                colecao.setNome(line.getGroup());
                                colecao.setIdsLivros(new ArrayList<>());
                                repository.getData().getColecoes().add(colecao);
                            }
                            colecao.getIdsLivros().add(livro.getId());
                        }

                        livro.setIdsEtiquetas(new ArrayList<>());
                        if ((line.getTags() != null) && (line.getTags().length() > 0)) {
                            String[] tags = line.getTags().split(",");
                            for (String tag : tags) {
                                Etiqueta etiqueta = repository.getData().buscaEtiquetaPorNome(tag);
                                if (etiqueta == null) {
                                    etiqueta = new Etiqueta();
                                    etiqueta.setId(Utils.generateId());
                                    etiqueta.setNome(tag);
                                    repository.getData().getEtiquetas().add(etiqueta);
                                }
                                livro.getIdsEtiquetas().add(etiqueta.getId());
                            }
                        }

                        // TODO status
                        // TODO began
                        // TODO completed

                        break;

                    default:
                        throw new RuntimeException("ItemType \"" + line.getItemType() + "\" desconhecido");
                }
            }
            repository.commitTransaction();
        } catch (Exception e) {
            repository.rollbackTransaction();
            throw e;
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
