package training.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Monarchy {
    public class Person {
        String name;
        ArrayList<Person> children = new ArrayList<>();
        boolean isDead;
        public Person(String name) {
            this.name = name;
        }
        public void addChildren(Person person) {
            children.add(person);
        }

        public void dead() {
            isDead = true;
        }

        public ArrayList<Person> getChildren() {
            return children;
        }
   }

        Person headOfTheMonarchy;
        Map<String, Person> map = new HashMap();

        public Monarchy(String name) {
            this.headOfTheMonarchy = new Person(name);
            map.put(name,headOfTheMonarchy);
        }
        public void birth(String name, String parent) {
            Person p = map.get(parent);
            if (p != null) {
                Person c = new Person(name);
                p.addChildren(c);
                map.put(name, c);
            } else {
                throw new RuntimeException("No parent found with name: " + parent);
            }
        }

        public void death(String name) {
            Person p = map.get(name);
            if (p != null) {
                p.dead();
            } else {
                throw new RuntimeException("No person found with name: " + name);
            }
        }

        public Object[] getSuccession() {
            ArrayList<String> successionList = new ArrayList<>();
            Stack<Person> stack = new Stack<>();
            stack.push(this.headOfTheMonarchy);
            while (!stack.isEmpty()) {
                Person p = stack.pop();
                if (!p.isDead) successionList.add(p.name);
                for (int i = p.getChildren().size()-1; i >= 0; i--) {
                    Person c = p.children.get(i);
                    stack.push(c);
                }
            }
            return successionList.toArray();
        }

    public static void main(String[] args) {
        Monarchy monarchy = new Monarchy("Jake");
        monarchy.birth("Catherine", "Jake");
        monarchy.birth("Jane", "Catherine");
        monarchy.birth("Farah", "Jane");
        monarchy.birth("Tom", "Jake");
        monarchy.birth("Celine", "Jake");
        monarchy.birth("Mark", "Catherine");
        monarchy.birth("Peter", "Celine");
        System.out.println(Arrays.toString(monarchy.getSuccession()));
        monarchy.death("Jake");
        monarchy.death("Jane");
        System.out.println(Arrays.toString(monarchy.getSuccession()));
    }
}
