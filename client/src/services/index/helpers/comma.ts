export function numberWithCommas(x: number | string) {
    let num: number;
    if (typeof x === 'string') {
        num = Number.parseInt(x);
    }else {
        num = x;
    }
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}
