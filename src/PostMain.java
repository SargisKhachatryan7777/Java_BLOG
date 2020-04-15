import blog.Post;
import blog.PostNotFoundException;
import blog.PostStorageImpl;

import java.util.Date;
import java.util.Scanner;

import static blog.PostStorage.*;

public class PostMain {
    static Scanner scanner = new Scanner(System.in);
    static Date data = new Date();

static PostStorageImpl postStorage=new PostStorageImpl();
    public static void main(String[] args) throws PostNotFoundException {
        boolean isRun = true;
        while (isRun) {
            printCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                    case EXIT:
                        isRun = false;
                        System.out.println("EXIT");
                        break;
                    case ADD_POST:
                        addPost();
                        break;
                    case SEARCH_POST:
                        searchPost();
                        break;
                    case POSTS_BY_CATEGORY:
                        postsByCategory();
                        break;
                    case ALL_POSTS:
                        allPosts();
                        break;
                default:
                    System.out.println("Wrong comand");
                }
            }
        }

    private static void addPost() {
        try {
            System.out.println("Please input Post data: title, text, category");
            String postDataStr = scanner.nextLine();
            String[] postData = postDataStr.split(",");
            Post post = new Post();
            post.setTitle(postData[0]);
            post.setText(postData[1]);
            post.setCategory(postData[2]);
            post.setCreatedDate(data);
            postStorage.add(post);
            System.out.println("Post was added!");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Wrong comand");
        }
    }

    private static void searchPost() throws PostNotFoundException {
        System.out.println("Please input Post name");
            String postName = scanner.nextLine();
            try {
                postStorage.getPostByTitle(postName);
            } catch (PostNotFoundException e){
            }
                postStorage.searchPostsByKeyword(postName);
        }

    private static void postsByCategory() {
        System.out.println("Please input Post category");
        String postName = scanner.nextLine();
        postStorage.printPostsByCategory(postName);
    }

    private static void allPosts() {
        postStorage.print();
        }
    private static void printCommands() {
            System.out.println("Please input " + EXIT + " for EXIT");
            System.out.println("Please input " + ADD_POST + " for ADD_POST");
            System.out.println("Please input " + SEARCH_POST + " for SEARCH_POST");
            System.out.println("Please input " + POSTS_BY_CATEGORY + " for POSTS_BY_CATEGORY");
            System.out.println("Please input " + ALL_POSTS + " for ALL_POSTS");
        }
    }
