import java.awt.Font;

class Sort {
	static int delay;
	public static void swap(double [] a, int i, int j)
	{
		double tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static void show(double [] a, double hi, int first, int second, String title)
	{
		int N = a.length;
		StdDraw.clear(StdDraw.WHITE);
		for (int i = 0; i < N; i++) {
			StdDraw.setPenColor(StdDraw.GRAY);
			if (i == first || i == second) {
				StdDraw.setPenColor(StdDraw.RED);
			}
			StdDraw.line(i, 0, i, hi * a[i]);
			StdDraw.text(i, -0.08, Double.toString(a[i]), 90);
		}
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.text(- 1, 0, title);
		StdDraw.show(delay);

	}

	public static void initDraw(int N, int _delay)
	{
		StdDraw.show(2);
        StdDraw.setCanvasSize(1100, 900);
        StdDraw.setXscale(-2, N+1);
        StdDraw.setYscale(-1, 1.2);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.setPenRadius(.01);
        delay = _delay;
	}
}

class BubbleSort extends Sort{
	public static double [] sort (double [] a, double hi)
	{
		int N = a.length;
		double [] b = new double[N];

		for (int i = 0; i < N; i++) {
			b[i] = a[i];
		}

		for (int i = 0; i < N ; i ++ ) {
			for (int j = 1; j < N - i ; j ++ ) {
				if (b[j] > b[j - 1]) {
					Sort.swap(b, j, j - 1);
					show(b, hi, j, j - 1,  "Bubble           ");
				}
			}
		}

		return b;
	}
}

class SelectionSort extends Sort{
	public static double [] sort (double [] a, double hi)
	{
		int N = a.length;
		double [] b = new double[N];

		for (int i = 0; i < N; i++) {
			b[i] = a[i];
		}

		for (int i = 0; i < N ; i ++ ) {
			for (int j = i + 1; j < N  ; j ++ ) {
				Sort.show(b, hi, i, j,  "SELECTION           ");
				if (b[i] < b[j]) {
					Sort.swap(b, i, j);
					show(b, hi, j, j - 1,  "Selection           ");
				}
			}
		}

		return b;
	}
}

class InsertionSort extends Sort{
	public static double [] sort (double [] a, double hi)
	{
		int N = a.length;
		double [] b = new double[N];

		for (int i = 0; i < N; i++) {
			b[i] = a[i];
		}

		for (int i = 1 ; i < N ; i++)
		{
			for (int j = i ; j > 0; j--)
			{
				if (b[j-1] < b[j] ) {
					Sort.swap(b, j, j - 1);
					Sort.show(b, hi, j, j - 1,  "INSERTION           ");	
				} else 
				{	
					Sort.show(b, hi, j, j - 1,  "INSERTION           ");	
					break;
				}
			}

		}

		return b;
	}
}

class QuickSort extends Sort{
	public static double [] sort (double [] a, double hi)
	{
		int N = a.length;
		double [] b = new double[N];

		for (int i = 0; i < N; i++) {
			b[i] = a[i];
		}

		QuickSort.sort(b, 0, N - 1, hi);

		return b;
	}


	private static void sort(double[] a, int lo, int hi, double hi2) { 
        if (hi <= lo) return;
        int j = QuickSort.partition(a, lo, hi, hi2);
        QuickSort.sort(a, lo, j-1, hi2);
        QuickSort.sort(a, j+1, hi, hi2);
    }

    private static int partition(double[] a, int lo, int hi, double hi2) {
        int i = lo;
        int j = hi + 1;
        double v = a[lo];
        while (true) { 

            while (a[++i] > v)
            {
                if (i == hi) break;
            }
            while (v > a[--j])
            {
                if (j == lo) break;
            }

            if (i >= j) break;

            Sort.swap(a, i, j);;
            Sort.show(a, hi2, j, i,  "QUICK           ");
        }

        Sort.swap(a, lo, j);
        Sort.show(a, hi2, j, lo,  "QUICK           ");

        return j;
    }
}

class MergeSort extends Sort{
	private static void merge(double[] a, double[] aux, int lo, int mid, int hi, double hi2) {

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)
            {
            	a[k] = aux[j++];
            }
            else if (j > hi)
            {
            	a[k] = aux[i++];
            }
            else if (aux[j] > aux[i])
            {
            	a[k] = aux[j++];
            }
            else
        	{
        		a[k] = aux[i++];
        	}
        	Sort.show(a, hi2, k, -1,  "MERGE           ");
        }
    }

    private static void sort(double[] a, double[] aux, int lo, int hi, double hi2) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        MergeSort.sort(a, aux, lo, mid, hi2);
        MergeSort.sort(a, aux, mid + 1, hi, hi2);
        merge(a, aux, lo, mid, hi, hi2);
    }


    public static double [] sort(double[] a, double hi) {

    	int N = a.length;
		double [] b = new double[N];

		for (int i = 0; i < N; i++) {
			b[i] = a[i];
		}

        double[] aux = new double[N];
        MergeSort.sort(b, aux, 0, N -1, hi);

        return b;
    }
}

class ShellSort extends Sort{
	public static double [] sort(double[] a, double hi) {
        int N = a.length;
		double [] b = new double[N];

		for (int i = 0; i < N; i++) {
			b[i] = a[i];
		}


        int k = 1;
        int h = 1;
        while (h < N/3) {
            h = 3*h + 1;
            k++;
        }

        while (h >= 1) { 
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h; j -= h) {
                    Sort.show(b, hi, j, j - h,  "SHELL           ");
                    if (b[j] > b[j-h]) {
                    	Sort.swap(b, j, j-h);
                    } else break;
                }
            }
            h /= 3;
        }
        return b;
    }
}

class allSorts {
	
	public static void main(String[] args) {
		if (args.length == 0 || args.length == 1) {
			System.out.println("Help: ");
			System.out.println("java allSort [Algorithm Name] [Count] <delay>\n");
			System.out.println("Algorithm Names: ");
			System.out.println("bubble:  Bubble Sort");
			System.out.println("selection: Selection Sort");
			System.out.println("insertion: Insertion Sort");
			System.out.println("quick: Quick Sort");
			System.out.println("merge: Merge Sort");
			System.out.println("shell: Shell Sort");
			System.out.println("\nDelay: ");
			System.out.println("time for each compare to show graphics in milisecond ");
			return;

		}
		String sortName = args[0];
		int N = Integer.parseInt(args[1]);
		int delay = 50;
		if (args.length >= 3) {
			delay = Integer.parseInt(args[2]);
		}
        double [] unsortedArray = new double[N];
        double [] sortedArray;
        for (int i = 0; i < N; i++)
			unsortedArray[i] = StdRandom.uniform(10000);

		System.out.println("Unsorted Array:");

		for (int i = 0; i < N; i++) {
			System.out.print(unsortedArray[i] + "\t");
		}
		System.out.println("");

		Sort.initDraw(N, delay);
		switch (sortName) {
			case "bubble":
				sortedArray = BubbleSort.sort(unsortedArray, 0.00008);
				break;
			case "selection": 
				sortedArray = SelectionSort.sort(unsortedArray, 0.00008);
				break;
			case "insertion":
				sortedArray = InsertionSort.sort(unsortedArray, 0.00008);
				break;
			case "quick":
				sortedArray = QuickSort.sort(unsortedArray, 0.00008);
				break;
			case "merge":
				sortedArray = MergeSort.sort(unsortedArray, 0.00008);
				break;
			case "shell":
				sortedArray = ShellSort.sort(unsortedArray, 0.00008);
				break;
			default:
				sortedArray = BubbleSort.sort(unsortedArray, 0.00008);
		}

		System.out.println("Sorted Array:");

		for (int i = 0; i < N; i++) {
			System.out.print(sortedArray[i] + "\t");
		}
		System.out.println("");
		
	}
}
