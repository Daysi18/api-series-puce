package com.puce.series.api_series.service;

import com.puce.series.api_series.models.Serie;
import com.puce.series.api_series.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public List<Serie> listarTodas() {
        return serieRepository.findAll();
    }

    public Optional<Serie> buscarPorId(Long id) {
        return serieRepository.findById(id);
    }

    public Serie guardar(Serie serie) {
        return serieRepository.save(serie);
    }

    public void eliminar(Long id) {
        serieRepository.deleteById(id);
    }
}