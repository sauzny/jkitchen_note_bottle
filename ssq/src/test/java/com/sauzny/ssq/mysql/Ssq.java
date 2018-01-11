package com.sauzny.ssq.mysql;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.sauzny.ssq.jsoup.SsqJsoup;

public class Ssq {
    
    public static final String sqlTem = "select ${ballName} as num, (count(${ballName}) * (${next}-max(id))) as score from (SELECT * from ssq ORDER BY id DESC limit 100) as tablea GROUP BY num ORDER BY score DESC";
    
    public static Integer next() throws SQLException{
        
        Object object = MysqlUtils.selectScalarHandler("SELECT max(id) as maxId FROM ssq");
        
        int next = Integer.parseInt(object.toString()) + 1;
        
        //System.out.println("next = " + next);
        
        return next;
    }
    
    public static void insert() throws SQLException, Exception{

        Object objectId = MysqlUtils.selectScalarHandler("SELECT max(id) as maxId FROM ssq");
        
        int nextId = Integer.parseInt(objectId.toString()) + 1;
        
        Object objectQihao = MysqlUtils.selectScalarHandler("SELECT max(qihao) as maxQihao FROM ssq");
        
        int nextQihao = Integer.parseInt(objectQihao.toString()) + 1;
        
        //System.out.println("nextQihao = " + nextQihao);
        
        List<String> list = SsqJsoup.last(nextQihao);
        
        int qihao = Integer.parseInt(list.get(0));
        String riqi = "'" + list.get(1) + "'";
        int shunxu1 = Integer.parseInt(list.get(2));
        int shunxu2 = Integer.parseInt(list.get(3));
        int shunxu3 = Integer.parseInt(list.get(4));
        int shunxu4 = Integer.parseInt(list.get(5));
        int shunxu5 = Integer.parseInt(list.get(6));
        int shunxu6 = Integer.parseInt(list.get(7));
        int lan = Integer.parseInt(list.get(8));
        
        SsqEntity ssqEntity = new SsqEntity(nextId, qihao, riqi, shunxu1, shunxu2, shunxu3, shunxu4, shunxu5, shunxu6, lan);
        
        String insert_sql = "insert into ssq values("+ssqEntity.allValues()+")";
        
        //System.out.println(insert_sql);
        
        MysqlUtils.insert_test(insert_sql);
        
    }
    
    public static SsqEntity selectLast() throws Exception {
        
        String sql = "select * from ssq ORDER BY id DESC limit 1";
        
        List<SsqEntity> list = MysqlUtils.select_bean_list(sql, SsqEntity.class);
        
        return list.get(0);
    }
    
    public static List<YuCeTemp> select(String ballName, String target) throws Exception{

        String sql = sqlTem.replace("${ballName}", ballName).replace("${next}", target);
        
        //System.out.println(sql);
        
        List<YuCeTemp> list = MysqlUtils.select_bean_list(sql, YuCeTemp.class);
        
        return list;
    }
    
    public static List<Integer> yuCe() throws Exception{

        List<Integer> result = Lists.newArrayList();
        
        Multiset<Integer> sortedMultiset = HashMultiset.create();
        ListMultimap<Integer, Integer> listMultimap = ArrayListMultimap.create();
        List<YuCeTemp> listz = Lists.newArrayList();
        
        List<List<YuCeTemp>> list = Lists.newArrayList();
        
        String target = String.valueOf(Ssq.next());
        
        for(int i=0;i<6;i++){
            list.add(Ssq.select("shunxu"+(i+1), target));
        }
        
        for(int i=0;i<5;i++){
            for(int j=0;j<6;j++){
                System.out.print(list.get(j).get(i)+"\t");
                YuCeTemp temp = list.get(j).get(i);
                int key = temp.getNum();
                int value = temp.getScore();
                sortedMultiset.add(list.get(j).get(i).getNum());
                listMultimap.put(key, value);
            }
            System.out.println();
        }
        
        for(Integer key : listMultimap.keySet()){
            Integer valueAll = 0;
            for(Integer value : listMultimap.asMap().get(key)){
                valueAll += value;
            }
            YuCeTemp temp = new YuCeTemp();
            temp.setNum(key);
            temp.setScore(valueAll);
            listz.add(temp);
        }
        
        listz.sort((h1, h2) -> h2.getScore() - h1.getScore());
        
        System.out.println("----");
        
        List<YuCeTemp> lanList = Ssq.select("lan", target);
        /*
        for(int i=0;i<16;i++){
            System.out.println(lanList.get(i));
        }
        */
        // 此处替换为 java8 的写法
        lanList.forEach(System.out::println);
        
        
        System.out.println("----");
        System.out.println(sortedMultiset);
        System.out.println(listMultimap);
        System.out.println(listz);

        System.out.println("----");
        /*
        System.out.print(listz.get(0).getNum() + " ");
        System.out.print(listz.get(1).getNum() + " ");
        System.out.print(listz.get(2).getNum() + " ");
        System.out.print(listz.get(3).getNum() + " ");
        System.out.print(listz.get(4).getNum() + " ");
        System.out.print(listz.get(5).getNum() + " ");
        System.out.println(lanList.get(0).getNum());
        */
        for(int i=0;i<6;i++){
            result.add(listz.get(i).getNum());
        }
        result.add(lanList.get(0).getNum());
        
        System.out.println(result);
        
        return result;
    }
    
    @Test
    public void foo01() throws Exception{
        
        insert();
        yuCe();
        
        //SsqEntity ssqEntity = selectLast();
        //System.out.println(ssqEntity.allValues());
    }
}

