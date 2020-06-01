import autobind from 'autobind-decorator';
import { action, observable, reaction } from 'mobx';

export interface IMatch {
    category: string,
    hashtag: string[],
    transaction: string,
    minPrice: number,
    maxPrice: number,
}

export const initialMatch = {
};

@autobind
class MatchStore {
    @observable title: string = '';
    @observable category: string = '';
    @observable hashtag: string[] = [];
    @observable transaction: string = '';
    @observable minPrice: number = 0;
    @observable maxPrice: number = 0;

    constructor(initialData = initialMatch, root: any) {

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
        return this.hashtag;
    }

    @action
    setHashtag(hashtag: string[]) {
        this.hashtag = hashtag;
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
