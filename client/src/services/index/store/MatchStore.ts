import autobind from 'autobind-decorator';
import { action, observable, toJS } from 'mobx';

export interface IMatch {
    category: string,
    hashtag: string[],
    transaction: string,
    minPrice: number,
    maxPrice: number,
}

export const initialMatch = {
    hashtag: [],
};

@autobind
class MatchStore {
    hashSet: Set<string> = new Set();
    match: IMatch | undefined;
    @observable title: string = '';
    @observable category: string = '';
    @observable hashtag: string[] = [];
    @observable transaction: string = '';
    @observable minPrice: number = 0;
    @observable maxPrice: number = 0;
    @observable tag: string = ''

    constructor(root: any, initialData?: MatchStore) {
        if (initialData) {
            this.match = {
                category: '',
                hashtag: Array(),
                transaction: '',
                maxPrice: 999999999,
                minPrice: 0,
            };
            this.tag = '';
        } else {
            this.match = {
                category: '',
                hashtag: Array(),
                transaction: '',
                maxPrice: 999999999,
                minPrice: 0,
            };
            this.tag = '';
        }
    }

    @action
    getMatch() {
        this.match = {
            category: this.category,
            hashtag: toJS(this.hashtag),
            transaction: this.transaction,
            maxPrice: this.maxPrice,
            minPrice: this.minPrice,
        }
        return this.match;
    }

    @action
    getTitle() {
        return this.title;
    }

    @action
    setTitle(title: string) {
        this.title = title;
    }

    @action
    getCategory() {
        return this.category;
    }

    @action
    setCategory(category: string) {
        this.category = category;
    }

    @action
    getHashtag() {
        return toJS(this.hashtag);
    }

    @action
    setHashtag(hashtag: string) {
        this.hashSet.add(hashtag);
        this.hashtag = Array.from(this.hashSet.values());
    }

    @action
    removeHashtag(hashtag: string) {
        this.hashSet.delete(hashtag);
        this.hashtag = Array.from(this.hashSet.values());
    }


    @action
    getTag() {
        return this.tag;
    }

    @action
    setTag(tag: string) {
        this.tag = tag;
    }

    @action
    getTransaction() {
        return this.transaction;
    }

    @action
    setTransaction(transaction: string) {
        this.transaction = transaction;
    }

    @action
    getMinPrice() {
        return this.minPrice;
    }

    @action
    setMinPrice(minPrice: number) {
        this.minPrice = minPrice;
    }

    @action
    getMaxPrice() {
        return this.maxPrice;
    }

    @action
    setMaxPrice(maxPrice: number) {
        this.maxPrice = maxPrice;
    }
}

export default MatchStore;
