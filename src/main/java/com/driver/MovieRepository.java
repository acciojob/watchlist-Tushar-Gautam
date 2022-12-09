package com.driver;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.HashSet;

@Component
public class MovieRepository {

    HashMap<String, Movie> movieHashMap;
    HashMap<String, Director> directorHashMap;
    HashMap<String, List<String>> movieDirectorHashMap;

    public MovieRepository() {
        this.movieHashMap = new HashMap<>();
        this.directorHashMap = new HashMap<>();
        this.movieDirectorHashMap = new HashMap<>();
    }

    public void addMovie(Movie movie){
        movieHashMap.put(movie.getName(),movie);
    }

    public void addDirector(Director director){
        directorHashMap.put(director.getName(),director);
    }

    public void addMovieDirectorPair(String movie, String director){
        if(movieHashMap.containsKey(movie) && directorHashMap.containsKey(director)){
            movieHashMap.put(movie, movieHashMap.get(movie));
            directorHashMap.put(director, directorHashMap.get(director));
            List<String> currentMovies = new ArrayList<String>();
            if(movieDirectorHashMap.containsKey(director)) currentMovies = movieDirectorHashMap.get(director);
            currentMovies.add(movie);
            movieDirectorHashMap.put(director, currentMovies);
        }
    }

    public Movie getMovieByName(String name){
        return movieHashMap.get(name);
    }

    public Director getDirectorByName(String name){
        return directorHashMap.get(name);
    }

    public List<String> getMoviesByDirectorName(String name){
        List<String> movieListByDirector = new ArrayList<>();
        if(movieDirectorHashMap.containsKey(name))
        {
            movieListByDirector = movieDirectorHashMap.get(name);
        }
        return movieListByDirector;
        /*for(Map.Entry<String,Movie> entry : movieHashMap.entrySet()){
            if(entry.getKey().equals(name))
                movieListByDirector.add(entry.getKey());
        }
        return movieListByDirector;*/
    }

    public List<String> findAllMovies() {
        /*List<String> movieList = new ArrayList<>();
        for(Map.Entry<String,Movie> entry : movieHashMap.entrySet()){
            movieList.add(entry.getKey());
        }
        return movieList;*/
        return new ArrayList<>(movieHashMap.keySet());
    }

    public void deleteDirectorByName(String directorName) {
        List<String> movieListByDirector = new ArrayList<>();
        if (movieDirectorHashMap.containsKey(directorName)) {
            movieListByDirector = movieDirectorHashMap.get(directorName);
            for (String movie : movieListByDirector){
                if(movieHashMap.containsKey(movie)){
                    movieHashMap.remove(movie);
                }
            }
            movieDirectorHashMap.remove(directorName);
        }
        if(directorHashMap.containsKey(directorName))
        directorHashMap.remove(directorName);
    }

    public void deleteAllDirectors() {
        HashSet<String> moviesSet = new HashSet<String>();
        for(String director: movieDirectorHashMap.keySet()){
            for(String movie: movieDirectorHashMap.get(director)){
                moviesSet.add(movie);
            }
        }

        for(String movie: moviesSet){
            if(movieHashMap.containsKey(movie)){
                movieHashMap.remove(movie);
            }
        }
    }
}
