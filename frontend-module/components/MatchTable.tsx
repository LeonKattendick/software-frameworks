import { CheckCircleTwoTone, CloseCircleTwoTone } from '@ant-design/icons';
import { Table } from 'antd';
import { Match } from '../util/interfaces/Match';

export const MatchTable = ({ matches }: { matches: Match[] }) => {
  return (
    <Table dataSource={matches} size="small" bordered pagination={false} scroll={{ y: 600 }} style={{ minHeight: 600 }}>
      <Table.Column title="ID" width="10%" render={(_, record: Match) => record.match_id} sorter={(a, b) => a.match_id.localeCompare(b.match_id)} />
      <Table.Column title="Kills" render={(_, record: Match) => record.kills} sorter={(a, b) => a.kills - b.kills} />
      <Table.Column title="Tode" render={(_, record: Match) => record.deaths} sorter={(a, b) => a.deaths - b.deaths} />
      <Table.Column title="Assists" render={(_, record: Match) => record.assists} sorter={(a, b) => a.assists - b.assists} />
      <Table.Column title="Held" render={(_, record: Match) => record.champion_name} sorter={(a, b) => a.champion_name.localeCompare(b.champion_name)} />
      <Table.Column
        title="Gewonnen"
        render={(_, record: Match) =>
          record.win ? <CheckCircleTwoTone style={{ fontSize: 20 }} twoToneColor="green" /> : <CloseCircleTwoTone style={{ fontSize: 20 }} twoToneColor="red" />
        }
        sorter={(a, b) => (a.win === b.win ? 0 : a.win ? -1 : 1)}
      />
    </Table>
  );
};
