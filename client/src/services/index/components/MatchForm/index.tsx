import * as React from 'react';
import styled from '~/styled';

import { useRouter } from 'next/router';
import { useState } from 'react';
import { useCreateMatchingMutation } from '~/generated/graphql';
import CommonBtn from '../CommonBtn';

import DropdownIcon from '../../assets/img/form-dropdown.png';
import ExpandIcon from '../../assets/img/form-expand.png';
import useStores from '../../helpers/useStores';

export interface IMatchFormProps {
}

const category = ['디지털/가전', '가구/인테리어', '유아동/유아도서', '생활/가공식품', '스포츠/레저', '여성잡화', '여성의류', '남성패션/잡화', '게임/취미', '뷰티/미용', '반려동물용품', '도서/티켓/음반', '기타 중고물품'];

export default (props: IMatchFormProps) => {
    const router = useRouter();
    const store = useStores();
    const matchStore = store.matchStore;
    const mutation = useCreateMatchingMutation();
    const [match, setMatch] = useState(store.matchStore.getMatch());

    const handleChangeCategory = (event: any) => {
        matchStore.setCategory(event.target.value);
        setMatch(store.matchStore.getMatch());
    }
    const handleChangeMinPrice = (event: any) => {
        let targetValue = event.target.value+'';
        let parseValue = event.target.value;
        if(targetValue.startsWith('0')){
            parseValue = Number.parseInt(targetValue);
        }
        matchStore.setMinPrice(Number.parseInt(parseValue))
        setMatch(store.matchStore.getMatch());
    }
    const handleChangeMaxPrice = (event: any) => {
        let targetValue = event.target.value+'';
        let parseValue = event.target.value;
        if(targetValue.startsWith('0')){
            parseValue = Number.parseInt(targetValue);
        }
        matchStore.setMaxPrice(Number.parseInt(parseValue))
        setMatch(store.matchStore.getMatch());
    }
    const handleChangeTransaction = (event: any) => {
        matchStore.setTransaction(event.target.value);
        setMatch(store.matchStore.getMatch());
    }
    const handleClickHashtag = () => {
        router.push('/form/match/hashtag')
    }
    const handleSubmit = (event: React.MouseEvent<HTMLDivElement, MouseEvent>) => {
        event.preventDefault();
        mutation({
            variables: {
                input: {
                    category: match.category,
                    minPrice: match.minPrice,
                    maxPrice: match.maxPrice,
                    transaction: match.transaction,
                    hashtag: match.hashtag,
                },
            },
        }).then((res) => {
            // console.log(res);
            router.push(`/match/${res.data.createMatching.user.userId}`);
        }).catch((error) => {
            // console.log(error);
        })
    }

    return (
        <Wrapper>
            <FormContainer>
                <InputContainer>
                    <Select value={match.category} onChange={handleChangeCategory}> <Option value={''}> 카테고리 </Option>
                        {
                            category.map((category, index) => (
                                <Option key={index}>{category}</Option>
                            ))
                        }
                    </Select>
                </InputContainer>
                <InputContainer>
                    <Input type={'number'} value={match.minPrice ? match.minPrice : ''} placeholder={'최소금액'} min={0} onChange={handleChangeMinPrice} />
                    <Span>~</Span>
                    <Input type={'number'} value={match.maxPrice ? match.maxPrice : ''} placeholder={'최대금액'} min={0} onChange={handleChangeMaxPrice} />
                </InputContainer>
                <InputContainer>
                    <Select value={match.transaction} onChange={handleChangeTransaction}> <Option value={''}> 거래방법 </Option> <Option>직거래</Option> <Option>택배거래</Option></Select>
                </InputContainer>
                <InputContainer onClick={handleClickHashtag}>
                    <HashTag>해시태그 <HashTagContainer>{match.hashtag.length} 개</HashTagContainer>
                    </HashTag>
                </InputContainer>
            </FormContainer>
            <FooterContainer>
                <BtnContainer onClick={handleSubmit} >
                    <CommonBtn type={'disable'} text={'등록하기'} />
                </BtnContainer>
            </FooterContainer>
        </Wrapper>
    );
}

const Wrapper = styled.div`
    position: relative;
    width: 100%;
    height: 100%;
    padding: 1.6rem;
    grid-area: CC;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
`

const FormContainer = styled.div`
    width: 100%;
    height: 100%;
`

const InputContainer = styled.div`
    display: flex;
    align-items: center;
    width: 100%;
    height: 3.5rem;
    border-bottom: solid 1px #ddd;
    margin: 0.5rem 0;
    vertical-align: center;
`

const Input = styled.input`
    width: 40%;
    height: 2rem;
    font-size: 15px;
    font-weight: bold;
    border: solid 0;
    color: #929292;
    margin: auto;
    ::placeholder {
        color: #929292;
        font-weight: bold;
    }
    :focus {
        outline: none !important;
    }
`

const Span = styled.span`
    padding: 1rem;
    font-size: 15px;
    color: #929292;
    font-weight: bold;
`

const Select = styled.select`
    -webkit-appearance: none;
       -moz-appearance: none;
            appearance: none;
    background: url(${DropdownIcon}) no-repeat 95% 50%;
    width: 100%;
    height: 2rem;
    font-size: 15px;
    color: #929292;
    font-weight: bold;
    border: solid 0;
    padding: 0.2rem;
    :focus {
        outline: none !important;
    }
    ::selection {
        color: pink;
    }
`

const Option = styled.option`
    color: #929292;
    font-weight: bold;
`

const HashTag = styled.div`
    background: url(${ExpandIcon}) no-repeat 95% 50%;
    width: 100%;
    height: auto;
    font-size: 15px;
    color: #929292;
    font-weight: bold;
    border: solid 0;
    padding: 0.5rem 0.2rem;
`

const HashTagContainer = styled.div`
    display: block;
    float: right;
    padding: 0 5rem;
`

const BtnContainer = styled.div``

const FooterContainer = styled.div`
    display: flex;
    justify-content: flex-end;
    margin: 1.5rem 0 0 0;
`
