public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int p = hashPattern(pattern);
        int m = pattern.length();
        String sub = input.substring(0, m);
        RollingString rs = new RollingString(sub, m);
        for(int i = 0; i < input.length() - m + 1; i++) {
            if(rs.hashCode() == p) {
                if(rs.toString().equals(pattern)) {
                    return i;
                }
            } else {
                rs.addChar(input.charAt(m + i));
            }
        }
        return -1;
    }

    public static int hashPattern(String pattern) {
        int p = 0;
        for(int i = 0; i < pattern.length(); i ++) {
            p = (128 * p + (int)pattern.charAt(i)) % 6113;
        }
        return p;
    }

}
