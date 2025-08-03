import java.util.*;

class Solution {
    static class Song{
        int id;
        int plays;
        
        public Song(int id, int plays){
            this.id = id;
            this.plays = plays;
        }
    }
    
    
    public int[] solution(String[] genres, int[] plays) {
  
        // 장르별 총 재생 횟수 저장
        Map<String, Integer> genrePlayCount = new HashMap<>();
        
        // 장르별 노래(고유번호, 재생 횟수) 저장
        Map<String, List<Song>> genreSongs = new HashMap<>();
        
        for(int i=0; i<genres.length; i++){
            genrePlayCount.put(genres[i], genrePlayCount.getOrDefault(genres[i], 0)+plays[i]);
            genreSongs.putIfAbsent(genres[i], new ArrayList<>());
            genreSongs.get(genres[i]).add(new Song(i, plays[i]));
        }
        
        
        // 1. 재생 횟수 기준 장르 정렬
        List<String> sortedGenres = new ArrayList<>(genrePlayCount.keySet());
        sortedGenres.sort((a, b) -> genrePlayCount.get(b) - genrePlayCount.get(a));
        
        
        // 2. 재생 횟수 기준 내림차순, 같으면 고유번호 오름차순
        List<Integer> result = new ArrayList<>();
        for(String g : sortedGenres){
            List<Song> songs = genreSongs.get(g);
            
            songs.sort((a, b) ->{
                if(b.plays == a.plays){
                    return a.id - b.id;
                }
                return b.plays - a.plays;
            });
            
            result.add(songs.get(0).id);
            if(songs.size() > 1){
                result.add(songs.get(1).id);
            }
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

}