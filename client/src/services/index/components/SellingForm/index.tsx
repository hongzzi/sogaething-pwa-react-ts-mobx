import * as React from 'react';
import styled from '~/styled';

export interface ISellingFormProps {
}

export default (props: ISellingFormProps) => {
    return (
        <Wrapper>
            form
        </Wrapper>
    );
}

const Wrapper = styled.div`
    display: grid;
    grid-auto-rows: 56px minmax(75vh, 85vh) 60px;
    height: 100%;
    grid-template-areas:
    "CH"
    "CC"
    "CI"
    ;
`;
