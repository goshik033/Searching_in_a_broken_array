
/*
-- ПРИНЦИП РАБОТЫ --
Сначала бинарным поиском ищем индекс разрыва. После этого, в одной из частей, полученных с помощью разделения
одного большого массива точкой разрыва, на два маленьких ищем сам элемент тоже бинарным поиском

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Метод всегда находит точку разрыва, если она есть, если ее нет, то это значит, что массив отсортирован и
и можно сразу искать индекс нужного нам элемента. Если же элемента в массиве нет, то бинарный поиск его не найдет
и выведет -1, что нам и нужно.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --

В начале brokenSearch мы проверяем отсортирован ли массив, если отсортирован,
то вызывается binarySearch с временной сложностью O(log n).
Если массив не отсортирован, то мы бинарным поиском ищем точку разрыва с временной сложностью O(log n).
И после этого выполняем бинарный поиск о дной из частей массива за O(log n).
Итак, общая временная сложность алгоритма будет O(log n) + O(log n),
что можно упростить до O(log n)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Вызовы метода binarySearch занимает дополнительную память для стека вызовов,
максимальная глубина стека вызовов будет O(log n)
Дополнительные переменные: O(1)
Общая пространственная сложность алгоритма составит O(log n)

ID верной попытки 89686055.
*/

public class Solution {
    public static int brokenSearch(int[] arr, int k) {
        int left = 0;
        int index = 0;
        int right = arr.length - 1;
        int mid = left + (right - left) / 2;
        if (arr[left] < arr[mid] && arr[mid] < arr[right]) {
            return binarySearch(arr, index, arr.length - 1, k);
        } else {
            while (left <= right) {
                mid = left + (right - left) / 2;
                if (mid == arr.length - 1) {
                    index = mid;
                    break;
                }
                if (mid == 0) {
                    index = 1;
                    break;
                } else {
                    if (arr[mid - 1] > arr[mid]) {
                        index = mid;
                        break;
                    } else if (arr[mid] > arr[mid + 1]) {
                        index = mid + 1;
                        break;
                    } else {
                        if (arr[left] < arr[mid]) {
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }
                }
            }
            if (arr[index] <= k && k <= arr[arr.length - 1]) {
                return binarySearch(arr, index, arr.length - 1, k);
            } else {
                return binarySearch(arr, 0, index, k);
            }
        }
    }

    public static int binarySearch(int[] arr, int left, int right, int k) {
        int index = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < k) {
                left = mid + 1;
            } else if (arr[mid] > k) {
                right = mid - 1;
            } else if (arr[mid] == k) {
                index = mid;
                break;
            }
        }
        return index;
    }

    private static void test() {
        int[] arr = {5, 1};
        assert 6 == brokenSearch(arr, 1);
    }
}

