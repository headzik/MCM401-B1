/* Generated By:JavaCC: Do not edit this line. Compiler.java */
import java.io.InputStream;
import java.io.ByteArrayInputStream;

public class Compiler implements CompilerConstants {

        public Compiler() {}

  public static TreeNode evaluate(String _expr) throws ParseException {
    InputStream input = new ByteArrayInputStream(_expr.getBytes());
    Compiler compiler = new Compiler(input);
    TreeNode t = compiler.stmt();
                t.setVariableParameters(new ContextElement[]{new SpeedContext(4001, "speed", SpeedContext.SpeedType.KMH, 100), new TemperatureContext(2001, "temp", TemperatureContext.TemperatureType.CELSIUS, 25),new DensityContext(8001, "density", DensityContext.DensityType.CARS, 2)});
                try {
                        System.out.println(String.valueOf(t.calculate()));
                } catch(Exception _e){
                        _e.printStackTrace();
                }
    return t;
  }

        public static void main(String[] _argv) {
    try {
      TreeNode node = Compiler.evaluate(_argv[0]);
    } catch (Exception _e) {
      _e.printStackTrace();
    }
        }

  final public TreeNode stmt() throws ParseException {
  TreeNode nodeA = null;
  TreeNode root = null;
    nodeA = context_stmt();
    root = context_comparison(nodeA);
                                                            {if (true) return root;}
    throw new Error("Missing return statement in function");
  }

  final public TreeNode context_comparison(TreeNode _nodeA) throws ParseException {
  TreeNode root = null;
  TreeNode nodeB = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LESS:
      jj_consume_token(LESS);
      nodeB = context_stmt();
  root = new TreeNode_LESS();
  root.setChilds(new TreeNode[]{ _nodeA, nodeB });
  {if (true) return root;}
      break;
    case GREATER:
      jj_consume_token(GREATER);
      nodeB = context_stmt();
  root = new TreeNode_GREATER();
  root.setChilds(new TreeNode[]{ _nodeA, nodeB });
  {if (true) return root;}
      break;
    case EQUALS:
      jj_consume_token(EQUALS);
      nodeB = context_stmt();
  root = new TreeNode_EQUALS();
  root.setChilds(new TreeNode[]{ _nodeA, nodeB });
  {if (true) return root;}
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public TreeNode context_stmt() throws ParseException {
                            Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CONTEXT_SPEED:
      jj_consume_token(CONTEXT_SPEED);
                          {if (true) return new TreeNodeContextVar(TreeNodeContextVar.ContextType.SPEED);}
      break;
    case CONTEXT_TEMP:
      jj_consume_token(CONTEXT_TEMP);
                           {if (true) return new TreeNodeContextVar(TreeNodeContextVar.ContextType.TEMPERATURE);}
      break;
    case CONTEXT_DENSITY:
      jj_consume_token(CONTEXT_DENSITY);
                              {if (true) return new TreeNodeContextVar(TreeNodeContextVar.ContextType.DENSITY);}
      break;
    case DIGIT:
      t = jj_consume_token(DIGIT);
                  {if (true) return new TreeNodeDigit(t.image);}
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public CompilerTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[2];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1c0,0xe20,};
   }

  /** Constructor with InputStream. */
  public Compiler(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Compiler(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new CompilerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Compiler(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new CompilerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Compiler(CompilerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(CompilerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[12];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 2; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 12; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
