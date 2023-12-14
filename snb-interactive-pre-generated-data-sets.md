# Pre-generated data sets

## Streaming decompression

To download and decompress the data sets on-the-fly, make sure you have `curl` and [`zstd`](https://facebook.github.io/zstd/) installed, then run:

```bash
export DATASET_URL=...
curl --silent --fail ${DATASET_URL} | tar -xv --use-compress-program=unzstd
```

For multi-file data sets, first download them. Then, to recombine and decompress, run:

```bash
cat <data-set-filename>.tar.zst* | tar -xv --use-compress-program=unzstd
```

This command works on both standalone files (`.tar.zst`) and chunked ones (`.tar.zst.XXX`).


## Data sets links

### Data sets

Use the initial snapshots from the BI data sets, see <https://ldbcouncil.org/data-sets-surf-repository/snb-business-intelligence.html>.

### Update streams

See <https://ldbcouncil.org/data-sets-surf-repository/snb-interactive-v2-updates.html>.

### Factor tables

See <https://ldbcouncil.org/data-sets-surf-repository/snb-factor-tables.html>.
