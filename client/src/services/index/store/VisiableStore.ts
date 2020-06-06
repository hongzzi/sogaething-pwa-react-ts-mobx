import autobind from 'autobind-decorator';
import { action, observable, toJS } from 'mobx';

export interface IVisiable {
    visiable: boolean,
}

export const initialVisiable = {
    visiable: false,
};

@autobind
class VisiableStore {
    @observable visiable: boolean = false;

    constructor(root: any, initialData?: VisiableStore) {
        this.visiable = false;
    }

    @action
    getVisiable() {
        return this.visiable;
    }

    @action
    setOn() {
        this.visiable = true;
    }

    @action
    setOff() {
        this.visiable = false;
    }
}

export default VisiableStore;
