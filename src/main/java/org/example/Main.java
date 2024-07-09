package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int lastid = 0;
    static List<Article> list = new ArrayList<Article>();
    public static void main(String[] args) {
        System.out.println("== 명언 앱 실행 ==");
        while(true){
            System.out.print("명령어 ) ");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] words = input.split("\\?", 2);
            if(input.equals("나가기")){
                break;
            }
            if(input.equals("등록")){
                LocalDateTime now = LocalDateTime.now();
                String time =now.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss"));
                int id = lastid + 1;
                System.out.print("명언 : ");
                String body = sc.nextLine();
                System.out.print("작가 : ");
                String name = sc.nextLine();
                Article article = new Article(id, body, name,time);
                list.add(article);
                System.out.printf("%d번 명언이 등록되었습니다.\n",id);
                lastid++;
            }
            if(input.equals("목록")){
                System.out.println("번호  /  작가  /  명언");
                System.out.println("=".repeat(30));
                for(int i = list.size()-1; i >= 0; i--){
                    Article article = list.get(i);
                    System.out.printf("%d  /  %s  /  %s\n",article.getId(),article.getName(),article.getBody());
                }
            }
            if(words[0].equals("상세보기")){
                String[] a = words[1].split("\\=");
                if(a[0].equals("id")) {
                    if (Integer.parseInt(a[1]) <= list.size()) {
                        Article article = list.get(Integer.parseInt(a[1])-1);
                        System.out.println("번호 : " + article.getId());
                        System.out.println("날짜 : " + article.getTime());
                        System.out.println("작가 : " + article.getName());
                        System.out.println("내용 : " + article.getBody());
                    } else {
                        System.out.printf("%d번 명언은 존재하지 않습니다.\n", Integer.parseInt(a[1]));
                    }
                }else {
                    System.out.println("명령어를 다시 입력해 주세요.");
                    continue;
                }
            }
            if(words[0].equals("수정")){
                String[] a = words[1].split("\\=",2);
                if(a[0].equals("id")){
                    if(Integer.parseInt(a[1]) <= list.size()) {
                        LocalDateTime now = LocalDateTime.now();
                        String time =now.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss"));
                        Article article = list.get(Integer.parseInt(a[1])-1);
                        System.out.println("명언(기존) : " + article.getBody());
                        System.out.println("작가(기존) : " + article.getName());
                        System.out.print("명언 : ");
                        String body = sc.nextLine();
                        System.out.print("작가 : ");
                        String name = sc.nextLine();
                        Article article1 = new Article(Integer.parseInt(a[1]),name,body,time);
                        list.set(Integer.parseInt(a[1]),article1);
                        System.out.printf("%d번 명언이 수정되었습니다.\n",article1.getId());
                    }
                    else{
                        System.out.printf("%d번 명언은 존재하지 않습니다.\n",Integer.parseInt(a[1]));
                    }
                }else {
                    System.out.println("명령어를 다시 입력해 주세요.");
                    continue;
                }
            }
            if(words[0].equals("삭제")){
                String[] a = words[1].split("\\=");
                if(a[0].equals("id")){
                    if(Integer.parseInt(a[1]) <= list.size()) {
                        list.remove(Integer.parseInt(a[1])-1);
                        System.out.printf("%d번 명언이 삭제되었습니다.\n",Integer.parseInt(a[1]));
                    }
                    else{
                        System.out.printf("%d번 명언은 존재하지 않습니다.\n",Integer.parseInt(a[1]));
                    }
                }else {
                    System.out.println("명령어를 다시 입력해 주세요.");
                    continue;
                }
            }
        }
    }
}
class Article{
    public int id;
    public String name;
    public String body;
    public String time;
    public Article(int id, String name , String body, String time){
        this.id = id;
        this.name = name;
        this.body = body;
        this.time = time;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
   public String getBody(){
        return body;
   }
   public String getTime(){
        return time;
   }
}