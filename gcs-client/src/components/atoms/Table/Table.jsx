import styled from "styled-components";

const Tbl = styled.table`
  --bs-table-color: var(--bs-body-color);
  --bs-table-bg: transparent;
  --bs-table-border-color: var(--bs-border-color);
  --bs-table-accent-bg: transparent;
  --bs-table-striped-color: var(--bs-body-color);
  --bs-table-striped-bg: rgba(0, 0, 0, 0.05);
  --bs-table-active-color: var(--bs-body-color);
  --bs-table-active-bg: rgba(0, 0, 0, 0.1);
  --bs-table-hover-color: var(--bs-body-color);
  --bs-table-hover-bg: rgba(0, 0, 0, 0.075);
  width: 100%;
  margin-bottom: 1rem;
  color: var(--bs-table-color);
  vertical-align: top;
  border-color: var(--bs-table-border-color);
`;



const Table = ({columns, data}) => {
  return(
    <Tbl>
      <thead>
      {columns.map((column) => (
        <th key={column}>{column}</th>
      ))}
      </thead>
      <tbody>
      {data.map(d => {
        <tr key={1}>
          {columns.map((col) => {
          <td>{data.col}</td>
        })}
        </tr>
      })}
      </tbody>
    </Tbl>
    )
}

export default Table;
