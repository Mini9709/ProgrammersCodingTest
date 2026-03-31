import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static Ingredient[] ingredients;
    static Ingredient[] temp;

    static class Ingredient {
        int l;
        int r;
        long taste;
        long sum;

        Ingredient(int l, int r, long taste) {
            this.l = l;
            this.r = r;
            this.taste = taste;
            this.sum = taste;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        ingredients = new Ingredient[n];
        temp = new Ingredient[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int taste = Integer.parseInt(st.nextToken());

            ingredients[i] = new Ingredient(l, r, taste);
        }

        Arrays.sort(ingredients, (o1, o2) -> {
            if (o1.l != o2.l) {
                return Integer.compare(o1.l, o2.l);
            }
            return Integer.compare(o2.r, o1.r);
        });

        divide(0, n - 1);

        long answer = 0;

        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, ingredients[i].sum);
        }

        System.out.println(answer);
    }

    static void divide(int left, int right) {
        if (left == right) {
            return;
        }

        int mid = (left + right) / 2;

        divide(left, mid);
        divide(mid + 1, right);

        int i = left;
        int j = mid + 1;
        long acc = 0;

        while (i <= mid) {
            while (j <= right && ingredients[j].r <= ingredients[i].r) {
                acc += ingredients[j].taste;
                j++;
            }
            ingredients[i].sum += acc;
            i++;
        }

        merge(left, mid, right);
    }

    static void merge(int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int idx = left;

        while (i <= mid && j <= right) {
            if (ingredients[i].r <= ingredients[j].r) {
                temp[idx++] = ingredients[i++];
            } else {
                temp[idx++] = ingredients[j++];
            }
        }

        while (i <= mid) {
            temp[idx++] = ingredients[i++];
        }

        while (j <= right) {
            temp[idx++] = ingredients[j++];
        }

        for (int k = left; k <= right; k++) {
            ingredients[k] = temp[k];
        }
    }
}