<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang = "en">

<head>

<link rel="icon"          href="http://algs4.cs.princeton.edu/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="http://algs4.cs.princeton.edu/favicon.ico" type="image/x-icon">
<link rel="stylesheet"    href="http://algs4.cs.princeton.edu/java.css" type="text/css">

<title>WeightedQuickUnionUF.java</title>

<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
<meta NAME="AUTHOR" CONTENT="Robert Sedgewick and Kevin Wayne">
<meta NAME="DESCRIPTION" CONTENT="WeightedQuickUnionUF code in Java">
<meta NAME="TITLE" CONTENT="WeightedQuickUnionUF code in Java">
<meta NAME="KEYWORDS" CONTENT="WeightedQuickUnionUF,java,programming,computer science,algorithm,data structure,program,code">
<meta NAME="ROBOTS" CONTENT="INDEX,FOLLOW">

</head>


<body>
<center><h1>WeightedQuickUnionUF.java</h1></center><p><br>

Below is the syntax highlighted version of <a href = "WeightedQuickUnionUF.java">WeightedQuickUnionUF.java</a>
from <a href = "http://algs4.cs.princeton.edu/15uf">&#167;1.5 Case Study: Union-Find</a>.
<p><br>

<!-- Generator: GNU source-highlight 3.1.4
by Lorenzo Bettini
http://www.lorenzobettini.it
http://www.gnu.org/software/src-highlite -->
<pre><tt><span class="comment">/****************************************************************************</span>
<span class="comment"> *  Compilation:  javac WeightedQuickUnionUF.java</span>
<span class="comment"> *  Execution:  java WeightedQuickUnionUF &lt; input.txt</span>
<span class="comment"> *  Dependencies: StdIn.java StdOut.java</span>
<span class="comment"> *</span>
<span class="comment"> *  Weighted quick-union (without path compression).</span>
<span class="comment"> *</span>
<span class="comment"> ****************************************************************************/</span>

<span class="keyword">public</span><span class="normal"> </span><span class="keyword">class</span><span class="normal"> </span><span class="classname">WeightedQuickUnionUF</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="type">int</span><span class="symbol">[]</span><span class="normal"> id</span><span class="symbol">;</span><span class="normal">    </span><span class="comment">// id[i] = parent of i</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="type">int</span><span class="symbol">[]</span><span class="normal"> sz</span><span class="symbol">;</span><span class="normal">    </span><span class="comment">// sz[i] = number of objects in subtree rooted at i</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="type">int</span><span class="normal"> count</span><span class="symbol">;</span><span class="normal">   </span><span class="comment">// number of components</span>

<span class="normal">    </span><span class="comment">// Create an empty union find data structure with N isolated sets.</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="function">WeightedQuickUnionUF</span><span class="symbol">(</span><span class="type">int</span><span class="normal"> N</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        count </span><span class="symbol">=</span><span class="normal"> N</span><span class="symbol">;</span>
<span class="normal">        id </span><span class="symbol">=</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="type">int</span><span class="symbol">[</span><span class="normal">N</span><span class="symbol">];</span>
<span class="normal">        sz </span><span class="symbol">=</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="type">int</span><span class="symbol">[</span><span class="normal">N</span><span class="symbol">];</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> </span><span class="number">0</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;</span><span class="normal"> N</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            id</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> i</span><span class="symbol">;</span>
<span class="normal">            sz</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> </span><span class="number">1</span><span class="symbol">;</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">// Return the number of disjoint sets.</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="type">int</span><span class="normal"> </span><span class="function">count</span><span class="symbol">()</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> count</span><span class="symbol">;</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">// Return component identifier for component containing p</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="type">int</span><span class="normal"> </span><span class="function">find</span><span class="symbol">(</span><span class="type">int</span><span class="normal"> p</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">while</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">p </span><span class="symbol">!=</span><span class="normal"> id</span><span class="symbol">[</span><span class="normal">p</span><span class="symbol">])</span>
<span class="normal">            p </span><span class="symbol">=</span><span class="normal"> id</span><span class="symbol">[</span><span class="normal">p</span><span class="symbol">];</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> p</span><span class="symbol">;</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">   </span><span class="comment">// Are objects p and q in the same set?</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="type">boolean</span><span class="normal"> </span><span class="function">connected</span><span class="symbol">(</span><span class="type">int</span><span class="normal"> p</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> q</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> </span><span class="function">find</span><span class="symbol">(</span><span class="normal">p</span><span class="symbol">)</span><span class="normal"> </span><span class="symbol">==</span><span class="normal"> </span><span class="function">find</span><span class="symbol">(</span><span class="normal">q</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">  </span>
<span class="normal">   </span><span class="comment">// Replace sets containing p and q with their union.</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">union</span><span class="symbol">(</span><span class="type">int</span><span class="normal"> p</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> q</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> </span><span class="function">find</span><span class="symbol">(</span><span class="normal">p</span><span class="symbol">);</span>
<span class="normal">        </span><span class="type">int</span><span class="normal"> j </span><span class="symbol">=</span><span class="normal"> </span><span class="function">find</span><span class="symbol">(</span><span class="normal">q</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">i </span><span class="symbol">==</span><span class="normal"> j</span><span class="symbol">)</span><span class="normal"> </span><span class="keyword">return</span><span class="symbol">;</span>

<span class="normal">        </span><span class="comment">// make smaller root point to larger one</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal">   </span><span class="symbol">(</span><span class="normal">sz</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">&lt;</span><span class="normal"> sz</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">])</span><span class="normal"> </span><span class="cbracket">{</span><span class="normal"> id</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> j</span><span class="symbol">;</span><span class="normal"> sz</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">+=</span><span class="normal"> sz</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">];</span><span class="normal"> </span><span class="cbracket">}</span>
<span class="normal">        </span><span class="keyword">else</span><span class="normal">                 </span><span class="cbracket">{</span><span class="normal"> id</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> i</span><span class="symbol">;</span><span class="normal"> sz</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">+=</span><span class="normal"> sz</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">];</span><span class="normal"> </span><span class="cbracket">}</span>
<span class="normal">        count</span><span class="symbol">--;</span>
<span class="normal">    </span><span class="cbracket">}</span>


<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">main</span><span class="symbol">(</span><span class="normal">String</span><span class="symbol">[]</span><span class="normal"> args</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="type">int</span><span class="normal"> N </span><span class="symbol">=</span><span class="normal"> StdIn</span><span class="symbol">.</span><span class="function">readInt</span><span class="symbol">();</span>
<span class="normal">        </span><span class="usertype">WeightedQuickUnionUF</span><span class="normal"> uf </span><span class="symbol">=</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">WeightedQuickUnionUF</span><span class="symbol">(</span><span class="normal">N</span><span class="symbol">);</span>

<span class="normal">        </span><span class="comment">// read in a sequence of pairs of integers (each in the range 0 to N-1),</span>
<span class="normal">         </span><span class="comment">// calling find() for each pair: If the members of the pair are not already</span>
<span class="normal">        </span><span class="comment">// call union() and print the pair.</span>
<span class="normal">        </span><span class="keyword">while</span><span class="normal"> </span><span class="symbol">(!</span><span class="normal">StdIn</span><span class="symbol">.</span><span class="function">isEmpty</span><span class="symbol">())</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="type">int</span><span class="normal"> p </span><span class="symbol">=</span><span class="normal"> StdIn</span><span class="symbol">.</span><span class="function">readInt</span><span class="symbol">();</span>
<span class="normal">            </span><span class="type">int</span><span class="normal"> q </span><span class="symbol">=</span><span class="normal"> StdIn</span><span class="symbol">.</span><span class="function">readInt</span><span class="symbol">();</span>
<span class="normal">            </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">uf</span><span class="symbol">.</span><span class="function">connected</span><span class="symbol">(</span><span class="normal">p</span><span class="symbol">,</span><span class="normal"> q</span><span class="symbol">))</span><span class="normal"> </span><span class="keyword">continue</span><span class="symbol">;</span>
<span class="normal">            uf</span><span class="symbol">.</span><span class="function">union</span><span class="symbol">(</span><span class="normal">p</span><span class="symbol">,</span><span class="normal"> q</span><span class="symbol">);</span>
<span class="normal">            StdOut</span><span class="symbol">.</span><span class="function">println</span><span class="symbol">(</span><span class="normal">p </span><span class="symbol">+</span><span class="normal"> </span><span class="string">" "</span><span class="normal"> </span><span class="symbol">+</span><span class="normal"> q</span><span class="symbol">);</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">        StdOut</span><span class="symbol">.</span><span class="function">println</span><span class="symbol">(</span><span class="normal">uf</span><span class="symbol">.</span><span class="function">count</span><span class="symbol">()</span><span class="normal"> </span><span class="symbol">+</span><span class="normal"> </span><span class="string">" components"</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="cbracket">}</span>

</tt></pre>

<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-10811519-2");
pageTracker._trackPageview();
} catch(err) {}</script>

</body>

<p><br><address><small>
Copyright &copy; 2002&ndash;2010, Robert Sedgewick and Kevin Wayne.
<br>Last updated: Thu Aug 16 07:22:29 EDT 2012.
</small></address>

</html>
