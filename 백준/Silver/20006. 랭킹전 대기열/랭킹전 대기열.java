import java.io.*;
import java.util.*;

public class Main {
    
    public static class Player{
    int level;
    String name;
    
    Player(int level, String name){
        this.level = level;
        this.name = name;
    }
}

    public static class Room{
        int baseLevel; // 기준 레벨
        ArrayList<Player> list = new ArrayList<>(); // 들어온 플레이어 목록
        
        Room(int baseLevel){
            this.baseLevel = baseLevel;
        }
    }

    
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int p = Integer.parseInt(st.nextToken()); // 플레이어 수
        int m = Integer.parseInt(st.nextToken()); // 방의 정원
        
        ArrayList<Room> rooms = new ArrayList<>();
        
        for(int i=0; i<p; i++){
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken()); // 플레이어의 레벨
            String nickname = st.nextToken(); // 닉네임
            
            Player player = new Player(level, nickname);
            Room target = null;
            
            // 기존 방 탐색
            for(Room room : rooms){
                if(room.list.size()>= m) continue;
                if(level >= room.baseLevel - 10 && level <= room.baseLevel + 10){
                    target = room;
                    break;
                }
            }
            
            if (target == null){
                target = new Room(level);
                rooms.add(target);
            }
            
            target.list.add(player);
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(Room room: rooms){
            if(room.list.size() == m) sb.append("Started!").append("\n");
            else sb.append("Waiting!").append("\n");
            
            Collections.sort(room.list, (a, b) -> a.name.compareTo(b.name));
            for(Player pl : room.list){
                sb.append(pl.level).append(" ").append(pl.name).append("\n");
            }
        }
        
        System.out.println(sb.toString());
    }
}
