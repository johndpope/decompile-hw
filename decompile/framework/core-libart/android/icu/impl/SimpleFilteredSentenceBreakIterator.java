package android.icu.impl;

import android.icu.text.BreakIterator;
import android.icu.text.FilteredBreakIteratorBuilder;
import android.icu.text.UCharacterIterator;
import android.icu.util.BytesTrie.Result;
import android.icu.util.CharsTrie;
import android.icu.util.CharsTrieBuilder;
import android.icu.util.StringTrieBuilder.Option;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.text.CharacterIterator;
import java.util.HashSet;

public class SimpleFilteredSentenceBreakIterator extends BreakIterator {
    private CharsTrie backwardsTrie;
    private BreakIterator delegate;
    private CharsTrie forwardsPartialTrie;
    private UCharacterIterator text;

    public static class Builder extends FilteredBreakIteratorBuilder {
        static final int AddToForward = 2;
        static final int MATCH = 2;
        static final int PARTIAL = 1;
        static final int SuppressInReverse = 1;
        private HashSet<String> filterSet = new HashSet();

        public Builder(ULocale loc) {
            ICUResourceBundle breaks = ((ICUResourceBundle) UResourceBundle.getBundleInstance("android/icu/impl/data/icudt56b/brkitr", loc)).findWithFallback("exceptions").findWithFallback("SentenceBreak");
            if (breaks != null) {
                int size = breaks.getSize();
                for (int index = 0; index < size; index++) {
                    this.filterSet.add(((ICUResourceBundle) breaks.get(index)).getString());
                }
            }
        }

        public boolean suppressBreakAfter(String str) {
            if (this.filterSet == null) {
                this.filterSet = new HashSet();
            }
            return this.filterSet.add(str);
        }

        public boolean unsuppressBreakAfter(String str) {
            if (this.filterSet == null) {
                return false;
            }
            return this.filterSet.remove(str);
        }

        public BreakIterator build(BreakIterator adoptBreakIterator) {
            CharsTrieBuilder builder = new CharsTrieBuilder();
            CharsTrieBuilder builder2 = new CharsTrieBuilder();
            int revCount = 0;
            int fwdCount = 0;
            int subCount = this.filterSet.size();
            String[] ustrs = new String[subCount];
            int[] partials = new int[subCount];
            CharsTrie backwardsTrie = null;
            CharsTrie forwardsPartialTrie = null;
            int i = 0;
            for (String s : this.filterSet) {
                ustrs[i] = s;
                partials[i] = 0;
                i++;
            }
            i = 0;
            while (i < subCount) {
                int nn = ustrs[i].indexOf(46);
                if (nn > -1 && nn + 1 != ustrs[i].length()) {
                    int sameAs = -1;
                    for (int j = 0; j < subCount; j++) {
                        if (j != i) {
                            if (ustrs[i].regionMatches(0, ustrs[j], 0, nn + 1)) {
                                if (partials[j] == 0) {
                                    partials[j] = 3;
                                } else if ((partials[j] & 1) != 0) {
                                    sameAs = j;
                                }
                            }
                        }
                    }
                    if (sameAs == -1 && partials[i] == 0) {
                        StringBuilder prefix = new StringBuilder(ustrs[i].substring(0, nn + 1));
                        prefix.reverse();
                        builder.add(prefix, 1);
                        revCount++;
                        partials[i] = 3;
                    }
                }
                i++;
            }
            for (i = 0; i < subCount; i++) {
                if (partials[i] == 0) {
                    builder.add(new StringBuilder(ustrs[i]).reverse(), 2);
                    revCount++;
                } else {
                    builder2.add(ustrs[i], 2);
                    fwdCount++;
                }
            }
            if (revCount > 0) {
                backwardsTrie = builder.build(Option.FAST);
            }
            if (fwdCount > 0) {
                forwardsPartialTrie = builder2.build(Option.FAST);
            }
            return new SimpleFilteredSentenceBreakIterator(adoptBreakIterator, forwardsPartialTrie, backwardsTrie);
        }
    }

    public SimpleFilteredSentenceBreakIterator(BreakIterator adoptBreakIterator, CharsTrie forwardsPartialTrie, CharsTrie backwardsTrie) {
        this.delegate = adoptBreakIterator;
        this.forwardsPartialTrie = forwardsPartialTrie;
        this.backwardsTrie = backwardsTrie;
    }

    public int next() {
        int n = this.delegate.next();
        if (n == -1 || this.backwardsTrie == null) {
            return n;
        }
        this.text = UCharacterIterator.getInstance((CharacterIterator) this.delegate.getText().clone());
        do {
            this.text.setIndex(n);
            this.backwardsTrie.reset();
            if (this.text.previousCodePoint() != 32) {
                int uch = this.text.nextCodePoint();
            }
            Result r = Result.INTERMEDIATE_VALUE;
            int bestPosn = -1;
            int bestValue = -1;
            while (true) {
                uch = this.text.previousCodePoint();
                if (uch == -1) {
                    break;
                }
                r = this.backwardsTrie.nextForCodePoint(uch);
                if (!r.hasNext()) {
                    break;
                } else if (r.hasValue()) {
                    bestPosn = this.text.getIndex();
                    bestValue = this.backwardsTrie.getValue();
                }
            }
            if (r.matches()) {
                bestValue = this.backwardsTrie.getValue();
                bestPosn = this.text.getIndex();
            }
            if (bestPosn < 0) {
                return n;
            }
            if (bestValue == 2) {
                n = this.delegate.next();
                if (n == -1) {
                    return n;
                }
            } else if (bestValue != 1 || this.forwardsPartialTrie == null) {
                return n;
            } else {
                this.forwardsPartialTrie.reset();
                Result rfwd = Result.INTERMEDIATE_VALUE;
                this.text.setIndex(bestPosn);
                do {
                    uch = this.text.nextCodePoint();
                    if (uch == -1) {
                        break;
                    }
                    rfwd = this.forwardsPartialTrie.nextForCodePoint(uch);
                } while (rfwd.hasNext());
                if (!rfwd.matches()) {
                    return n;
                }
                n = this.delegate.next();
                if (n == -1) {
                    return n;
                }
            }
        } while (n != -1);
        return n;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SimpleFilteredSentenceBreakIterator other = (SimpleFilteredSentenceBreakIterator) obj;
        if (this.delegate.equals(other.delegate) && this.text.equals(other.text) && this.backwardsTrie.equals(other.backwardsTrie)) {
            z = this.forwardsPartialTrie.equals(other.forwardsPartialTrie);
        }
        return z;
    }

    public int hashCode() {
        return ((this.forwardsPartialTrie.hashCode() * 39) + (this.backwardsTrie.hashCode() * 11)) + this.delegate.hashCode();
    }

    public Object clone() {
        return (SimpleFilteredSentenceBreakIterator) super.clone();
    }

    public int first() {
        return this.delegate.first();
    }

    public int last() {
        return this.delegate.last();
    }

    public int next(int n) {
        throw new UnsupportedOperationException("next(int) is not yet implemented");
    }

    public int previous() {
        throw new UnsupportedOperationException("previous() is not yet implemented");
    }

    public int following(int offset) {
        throw new UnsupportedOperationException("following(int) is not yet implemented");
    }

    public int current() {
        return this.delegate.current();
    }

    public int preceding(int offset) {
        throw new UnsupportedOperationException("preceding(int) is not yet implemented");
    }

    public CharacterIterator getText() {
        return this.delegate.getText();
    }

    public void setText(CharacterIterator newText) {
        this.delegate.setText(newText);
    }
}
