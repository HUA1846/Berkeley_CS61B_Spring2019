public class OffByN implements CharacterComparator {

        private int n;

        public OffByN(int N) {
            n = N;
        }

        @Override
        public boolean equalChars(char x, char y) {
            if((x < 97 || x > 122) || (y < 97 || y > 122)) return false;
            if(x - y == n || x - y == -n) {
                return true;
            }
            return false;
        }
}
