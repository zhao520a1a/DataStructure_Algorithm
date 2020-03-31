package algorithm.queue;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @考点：实现特殊数据结构的能力及特殊算法的设计能力
 * @描述：猫狗队列,用两个队列来分别存放猫狗元素，再通过count的大小来判断入整个队列的次序；
 * @思路：
 * @复杂度：  时间复杂度O(1)   空间复杂度O(n)
 * @链接： https://www.nowcoder.com/practice/8a7e04cff6a54b7095b94261d78108f5?tpId=101&tqId=33168&tPage=1&rp=1&ru=/ta/programmer-code-interview-guide&qru=/ta/programmer-code-interview-guide/question-ranking
 */

public class DogCatQueue {

    private Queue<Pet> catQueue = new LinkedList();
    private Queue<Pet> dogQueue = new LinkedList();


    public void add(Pet pet) {
        if ("cat".equals(pet.getType())) {
            catQueue.add(pet);
        } else if ("dog".equals(pet.getType())) {
            dogQueue.add(pet);
        }
    }

    public Pet pollDog() {
        return dogQueue.poll();
    }

    public Pet pollCat() {
        return catQueue.poll();
    }

    public Pet pollAll() {
        if (!dogQueue.isEmpty() && !catQueue.isEmpty()) {
            if (dogQueue.peek().getCount() < catQueue.peek().getCount()) {
                return pollDog();
            } else {
                return pollCat();
            }
        } else if (!dogQueue.isEmpty()) {
            return pollDog();
        } else if (!catQueue.isEmpty()) {
            return pollCat();
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return dogQueue.isEmpty() && catQueue.isEmpty();
    }

    public boolean isDogEmpty() {
        return dogQueue.isEmpty();
    }

    public boolean isCatEmpty() {
        return catQueue.isEmpty();
    }

}


class Pet {
    String type;
    int id; //编号
    int count; //一个代表顺序的标识

    public Pet(String type, int id, int count) {
        this.type = type;
        this.id = id;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}


class Main {

    public static void main(String[] args) throws IOException {
        DogCatQueue dogCatQueue = new DogCatQueue();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int row = Integer.parseInt(in.readLine());

        int count = 0;  //代表入队列的顺序标识
        StringBuilder res = new StringBuilder();
        while (row-- > 0) {
            String[] strArr = in.readLine().split(" ");
            String opt = strArr[0];
            switch (opt) {
                case "add":
                    String type = strArr[1];
                    if (type.equals("dog")) {
                        dogCatQueue.add(new Pet("dog", Integer.parseInt(strArr[2]), count++));
                    } else {
                        dogCatQueue.add(new Pet("cat", Integer.parseInt(strArr[2]), count++));
                    }
                    break;
                case "pollAll":
                    while (!dogCatQueue.isEmpty()) {
                        Pet pet = dogCatQueue.pollAll();
                        res.append(pet.getType() + " " + pet.getId() + "\n");
                    }
                    break;
                case "pollDog":
                    while (!dogCatQueue.isDogEmpty()) {
                        Pet dog = dogCatQueue.pollDog();
                        res.append("dog " + dog.getId() + "\n");
                    }
                    break;
                case "pollCat":
                    while (!dogCatQueue.isCatEmpty()) {
                        Pet cat = dogCatQueue.pollCat();
                        res.append("cat " + cat.getId() + "\n");
                    }

                    break;
                case "isDogEmpty":
                    res.append(dogCatQueue.isDogEmpty() ? "yes\n" : "no\n");
                    break;
                case "isCatEmpty":
                    res.append(dogCatQueue.isCatEmpty() ? "yes\n" : "no\n");
                    break;
                case "isEmpty":
                    res.append(dogCatQueue.isEmpty() ? "yes\n" : "no\n");
                    break;
            }
        }
        System.out.println(res.substring(0, res.length() - 1));
    }
}









